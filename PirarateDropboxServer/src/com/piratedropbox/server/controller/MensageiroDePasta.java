package com.piratedropbox.server.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.TAG;

public class MensageiroDePasta extends Thread implements Runnable{
	
	private Socket conexao; 
	private Scanner teclado;
	private List<Object> emPasta;
	
	public MensageiroDePasta(Socket conexao, List<Object> emPasta){
		this.conexao = conexao;
		this.emPasta = emPasta;
	}
	
	@Override
	public void run() {
		enviarMensagem();
	}
	
	public void enviarMensagem(){
		try {
			System.out.println("Aqui <- ");
//			System.out.println("Antes de enviar1: "+Mensagem.mensagemToJson(msg));
			PrintStream envia = null;
			if (!emPasta.isEmpty()) {
				for (Object o : emPasta) {
					Mensagem msg = null;
					if (o instanceof Pasta) {
						msg = new Mensagem(TAG.SEEP, (Pasta) o);
					} else if (o instanceof Arquivo) {
						msg = new Mensagem(TAG.SEEP, (Arquivo) o);
					}
					envia = new PrintStream(conexao.getOutputStream());
					envia.println(Mensagem.mensagemToJson(msg));
					System.out.println("Apos enviar: " + Mensagem.mensagemToJson(msg));
				}
			} else {
				Mensagem msg = new Mensagem(TAG.SEEP);
				System.out.println("   >>>>>  "+ Mensagem.mensagemToJson(msg));
				envia = new PrintStream(conexao.getOutputStream());
				envia.println(Mensagem.mensagemToJson(msg));
				System.out.println("   <<<<<  "+ Mensagem.mensagemToJson(msg));

			}
			envia.close();
			conexao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fechaScanner(){
		try {
			conexao.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		teclado.skip("[\r\n]+");
	}
	
}

