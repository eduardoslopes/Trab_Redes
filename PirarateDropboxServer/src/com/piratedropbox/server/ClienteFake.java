package com.piratedropbox.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.piratedropbox.server.controller.Mensageiro;
import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.TAG;
import com.piratedropbox.server.model.Usuario;

public class ClienteFake {
	
	public static void main(String[] args){
		try {
			Socket conexao = new Socket("localhost", 12345);
			//System.out.println("Olá "+apelido + " seja bem vindo!");
			byte[] b = null;
			Mensagem m = new Mensagem(TAG.SEEP, 4);
			Mensageiro sender = new Mensageiro(conexao, m);	
			sender.start();
			
		} catch (UnknownHostException e) {
			System.out.println("Não foi possível conectar-se!");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

}
