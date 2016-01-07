package com.piratedropbox.midleserver.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Encaminha implements Runnable {
	
	private String msgCliente;
	
	Encaminha(String msgCliente) {
		this.msgCliente = msgCliente;
	}
	
	/*
	 * Inicia comunicacao com servidores
	 * Chama metodo de verificacao de carga
	 */
	@Override
	public void run() {
		try { 
			Socket socketServidor1 = new Socket("", 12345);
			Socket socketServidor2 = new Socket("", 12345);
			Socket escolhido = verificaServidor(socketServidor1, socketServidor2);
			PrintStream saida = new PrintStream(escolhido.getOutputStream());
			saida.println(this.msgCliente);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Verifica servidor menos carregado
	 * Retorna para o metodo run
	 */
	private Socket verificaServidor(Socket socketServidor1, Socket socketServidor2) throws IOException {
		String msgServidor1 = null;		
		PrintStream saida1 = new PrintStream(socketServidor1.getOutputStream());
		saida1.println("TRAFEGO");
		Scanner respostaServidor1 = new Scanner(socketServidor1.getInputStream());
		
		msgServidor1 = respostaServidor1.nextLine();
		
		String msgServidor2 = null;
		PrintStream saida2 = new PrintStream(socketServidor1.getOutputStream());
		saida2.println("TRAFEGO");
		Scanner respostaServidor2 = new Scanner(socketServidor1.getInputStream());
		
		msgServidor2 = respostaServidor2.nextLine();
		
		saida1.close();
		saida2.close();
		respostaServidor1.close();
		respostaServidor2.close();
		
		return Integer.parseInt(msgServidor1) < Integer.parseInt(msgServidor2) ? socketServidor1 : socketServidor2;
	}
}
