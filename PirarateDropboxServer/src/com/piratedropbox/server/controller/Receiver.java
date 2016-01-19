package com.piratedropbox.server.controller;

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
			String msg = null;
			
			msg = scanner.nextLine();
			if(msg.equals("TRAFEGO")){
				System.out.println("1");
				int trafego = VerificadorTrafego.verificaTrafego();
				EnviaTrafego et = new EnviaTrafego(sender, trafego, this);
				et.start();
			}else{
				Mensagem m = Mensagem.jsonToMensagem(msg);
				InterpreterMessage im = new InterpreterMessage();
				im.messageInterpreter(m);
			}
			
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
