package com.piratedropbox.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerController {
	private int porta;
	public static List<Thread> connections = new ArrayList<>();
	
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
				connections.add(receiver);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
