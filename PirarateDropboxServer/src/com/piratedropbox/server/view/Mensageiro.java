package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import com.piratedropbox.server.model.Mensagem;


public class Mensageiro extends Thread implements Runnable{
	
	Socket conexao; 
	String apelido; 
	Receiver receiver;
	Scanner teclado;
	private Mensagem msg;
	
	public Mensageiro(Socket conexao, String apelido, Mensagem msg,Receiver receiver){
		this.conexao = conexao;
		this.apelido = apelido;
		this.receiver = receiver;
		this.msg = msg;
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
				envia.println(Mensagem.mensagemToJson(msg));
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
