package com.piratedropbox.midleserver.controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MidleServer {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket midleServer = new ServerSocket(12345);
		
		while(true) {
			
			Socket socketCliente = midleServer.accept();
			Scanner leitorMsg = new Scanner(socketCliente.getInputStream());
			
			//Captura mensagem cliente
			String msgCliente = null;
			msgCliente = leitorMsg.nextLine();
			System.out.println(msgCliente);
			
			//Inicia thread para escolha do servidor e encaminhamento da mensagem
			Encaminha encaminhador = new Encaminha(msgCliente);
			Thread enviando = new Thread(encaminhador);
			enviando.start();
			
			leitorMsg.close();
			socketCliente.close();
		}
	}
}
