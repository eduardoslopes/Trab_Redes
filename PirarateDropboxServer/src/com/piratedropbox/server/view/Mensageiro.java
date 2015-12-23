package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Mensageiro extends Thread implements Runnable{
	
	Socket conexao; 
	String apelido; 
	Receiver receiver;
	Scanner teclado;
	
	public Mensageiro(Socket conexao, String apelido, Receiver receiver){
		this.conexao = conexao;
		this.apelido = apelido;
		this.receiver = receiver;
	}
	
	@Override
	public void run() {
		enviarMensagem();
		super.run();
	}
	
	public void enviarMensagem(){
		try {
			teclado = new Scanner(System.in);
			PrintStream envia;
			envia = new PrintStream(conexao.getOutputStream());
			while(teclado.hasNextLine()){
				if(!receiver.isAlive()){
					break;
				}
				String texto = teclado.nextLine();
				if(!receiver.isAlive()){
					break;
				}
				Date data = new Date();
				Mensagem msg = new Mensagem(texto, data, apelido);
				envia.println(Mensagem.mensagemToJson(msg));
				if(msg.getMsg().toUpperCase().equals("TCHAU")){
					break;
				}
			}
			receiver.fecharSocket();
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
