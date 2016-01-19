package com.piratedropbox.model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import com.piratedropbox.model.Mensagem;


public class Mensageiro extends Thread implements Runnable{
	
	private Socket conexao; 
	private Scanner teclado;
	private Mensagem msg;
	
	public Mensageiro(Socket conexao, Mensagem msg){
		this.conexao = conexao;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		enviarMensagem();
	}
	
	public void enviarMensagem(){
		try {
			PrintStream envia;
			envia = new PrintStream(conexao.getOutputStream());
			envia.println(Mensagem.mensagemToJson(msg));
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
