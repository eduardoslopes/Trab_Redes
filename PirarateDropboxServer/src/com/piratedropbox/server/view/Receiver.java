package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

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
				continua = imprimeMensagem(msg);
			}
			Mensagem m = Mensagem.jsonToMensagem(msg);
			System.out.println(m.getApelidoDono() + " saiu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean imprimeMensagem(String jsonMsg){
		Mensagem novaMensagem = Mensagem.jsonToMensagem(jsonMsg);
		System.out.println(novaMensagem.toString());
		if( (!novaMensagem.getApelidoDono().equals("Servidor")) && novaMensagem.getMsg().toUpperCase().equals("TCHAU")){
			return false;
		}
		return true;
	}
	
	public void fecharSocket(){
		try {
			sender.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
