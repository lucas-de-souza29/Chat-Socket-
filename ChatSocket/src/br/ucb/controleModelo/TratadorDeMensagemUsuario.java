package br.ucb.controleModelo;

import java.io.InputStream;
import java.util.Scanner;

public class TratadorDeMensagemUsuario implements Runnable {

	private InputStream usuario;
	private Servidor servidor;

	public TratadorDeMensagemUsuario(InputStream usuario, Servidor servidor) {
		this.usuario = usuario;
		this.servidor = servidor;
	}

	@Override
	public void run() {
		// Quando chegar uma msg manda para todos
		Scanner leitor = new Scanner(this.usuario);
		while (leitor.hasNextLine()) {
			System.out.println("Cliente : " + leitor);
			servidor.distribuiMensagem(leitor.nextLine());
		}
		leitor.close();
	}
}