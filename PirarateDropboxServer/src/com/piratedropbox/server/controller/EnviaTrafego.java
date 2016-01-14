package com.piratedropbox.server.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class EnviaTrafego extends Thread implements Runnable {
	private Socket conexao; 
	private int trafego;
		
	public EnviaTrafego(Socket conexao, int trafego){
		this.conexao = conexao;
		this.trafego = trafego;
	}
	
	@Override
	public void run() {
		enviarTrafego();
	}
	
	public void enviarTrafego(){
		try {
			System.out.println("Antes de enviar1: "+trafego);
			PrintStream envia;
			envia = new PrintStream(conexao.getOutputStream());
			envia.println(trafego+"");
			System.out.println("Depois de enviar: "+trafego);
			conexao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
