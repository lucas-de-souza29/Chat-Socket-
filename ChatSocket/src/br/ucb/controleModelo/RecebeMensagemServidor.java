package br.ucb.controleModelo;

import java.io.InputStream;
import java.util.Scanner;

public class RecebeMensagemServidor implements Runnable {
	private InputStream servidor;

	public RecebeMensagemServidor(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		// recebendo as msgs do servidor e imprime na tela
		Scanner leitor = new Scanner(this.servidor);
		while (leitor.hasNextLine()) {
			System.out.println(leitor.nextLine());
		}
	}

}
