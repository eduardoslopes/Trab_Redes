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
		
		Socket socketServidor1 = null;
		Socket socketServidor2 = null;
		try { 
			
			Socket escolhido = verificaServidor();
			
			PrintStream saida = new PrintStream(escolhido.getOutputStream());
			System.out.println("Enviando...");
			saida.println(this.msgCliente);
			System.out.println("Enviou");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Verifica servidor menos carregado
	 * Retorna para o metodo run
	 */
	private Socket verificaServidor() throws IOException {
		String msgServidor1 = null;	
		Socket trafego1 = new Socket("piratedropboxserver1.com", 12345);
//		Socket trafego1 = new Socket("127.0.0.1", 12345);
		PrintStream saida1 = new PrintStream(trafego1.getOutputStream());
		saida1.println("TRAFEGO");
		Scanner respostaServidor1 = new Scanner(trafego1.getInputStream());
		
		while(respostaServidor1.hasNextLine()) {
			msgServidor1 = respostaServidor1.nextLine();
			if(msgServidor1 != null) break;
		}
		System.out.println(msgServidor1);
		
		String msgServidor2 = null;
		Socket trafego2 = new Socket("piratedropboxserver2.com", 23456);
//		Socket trafego2 = new Socket("127.0.0.1", 23456);
		PrintStream saida2 = new PrintStream(trafego2.getOutputStream());
		saida2.println("TRAFEGO");
		Scanner respostaServidor2 = new Scanner(trafego2.getInputStream());
		
		while(respostaServidor2.hasNextLine()) {
			msgServidor2 = respostaServidor2.nextLine();
			if(msgServidor2 != null) break;
		}
		System.out.println(msgServidor2);
		
		respostaServidor1.close();
		respostaServidor2.close();
		trafego1.close();
		trafego2.close();
		
		if (msgServidor2 == null) {
			System.out.println("Enviando para servidor 1");
//			socketServidor1 = new Socket("piratedropboxserver1.com", 12345);
//			socketServidor2 = new Socket("piratedropboxserver2.com", 23456);
//			socketServidor1 = new Socket("127.0.0.1", 12345);
//			socketServidor2 = new Socket("127.0.0.1", 23456);
//			return new Socket("127.0.0.1", 12345);
			return new Socket("piratedropboxserver1.com", 12345);
		}
		else if (msgServidor1 == null) {
			System.out.println("Enviando para servidor 2");
//			return new Socket("127.0.0.1", 23456);
			return new Socket("piratedropboxserver2.com", 23456);
		}
		else if (Integer.parseInt(msgServidor1) <= Integer.parseInt(msgServidor2)) {
			System.out.println("Enviando para servidor 1");
//			return new Socket("127.0.0.1", 12345);
			return new Socket("piratedropboxserver1.com", 12345);
		}
		else {
			System.out.println("Enviando para servidor 2");
//			return new Socket("127.0.0.1", 23456);
			return new Socket("piratedropboxserver2.com", 23456);
		}
	}
}
