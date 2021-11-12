package br.ucb.controleModelo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public static void main(String[] args) throws IOException {
		new Servidor(8080).executa();
	}

	private int porta;
	private List<PrintStream> contatos;

	public Servidor(int porta) {
		this.porta = porta;
		this.contatos = new ArrayList<PrintStream>();
	}

	public void executa() throws IOException {
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Porta 8080 está aberta!");
		while (true) {
			// Acc um usuario
			Socket usuario = servidor.accept();
			System.out.println("Nova conexão com o usuario " + usuario.getInetAddress().getHostAddress());

			// Adiciona a saida do usuario
			PrintStream saida = new PrintStream(usuario.getOutputStream());
			this.contatos.add(saida);

			// Criando tratador de usuario em uma nova thread
			TratadorDeMensagemUsuario trataUsuario = new TratadorDeMensagemUsuario(usuario.getInputStream(), this);
			new Thread(trataUsuario).start();
		}
	}

	public void distribuiMensagem(String msg) {
		// Envia msg para todo mundo
		for (PrintStream usuario : this.contatos) {
			usuario.println(msg);
		}
	}
}
