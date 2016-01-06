package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

import com.piratedropbox.server.model.Mensagem;

public class Receiver extends Thread implements Runnable {

	private Socket sender;
	private Scanner scanner;
	
	public Receiver(Socket sender) {
		this.sender = sender;
	}
	
	@Override
	public void run() {
		recebe();
	}
	
	private void recebe(){
		try {
			scanner = new Scanner(this.sender.getInputStream());
			boolean continua = true;
			String msg = null;
			while(scanner.hasNextLine() && continua){
				msg = scanner.nextLine();
				continua = false;
			}
			Mensagem m = Mensagem.jsonToMensagem(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void fecharSocket(){
		try {
			sender.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
