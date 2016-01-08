package com.piratedropbox.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.piratedropbox.server.view.Mensageiro;
import com.piratedropbox.server.view.Receiver;


public class ServerController {
	private int porta;
	
	public ServerController(int porta){
		this.porta = porta;
	}
	
	public void executa(){
		
		try {
			ServerSocket server =  new ServerSocket(porta);
			while(true){
				Socket cliente = server.accept();
				System.out.println("Conexão Estabelecida!");
				
				Receiver receiver = new Receiver(cliente);
				receiver.start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
