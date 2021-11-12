package br.ucb.controleModelo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Usuario {
		public static void main(String[] args) throws UnknownHostException,IOException{
		       new Usuario("127.0.0.1",8080).executa();
		}	  	   
		private String apelido;
	    private String host;
	    private int porta;
	    
		public Usuario(String host, int porta){
	        this.host = host;
	        this.porta = porta;	
	    }

		public String getApelido() {
			return apelido;
		}

		public void setApelido(String apelido) {
			this.apelido = apelido;
		}
		
		public void executa() throws UnknownHostException,IOException {
			   Socket usuario = new Socket(this.host,this.porta);
			   System.out.println("O usuario conectou ao servidor!");
			   RecebeMensagemServidor recebe = new RecebeMensagemServidor(usuario.getInputStream());
		       new Thread(recebe).start();
		       // leitura das msgs e envia para o servidor
		       Scanner bufferTeclado = new Scanner(System.in);
		       PrintStream saida = new PrintStream(usuario.getOutputStream());
		       while(bufferTeclado.hasNextLine()){
		    	  saida.println(bufferTeclado.nextLine()); 
		       }
		       saida.close();
		       bufferTeclado.close();
		       usuario.close();
		}
}
