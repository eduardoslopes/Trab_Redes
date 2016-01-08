package com.piratedropbox.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.TAG;
import com.piratedropbox.server.model.Usuario;
import com.piratedropbox.server.view.Mensageiro;

public class ClienteFake {
	
	public static void main(String[] args){
		try {
			Socket conexao = new Socket("localhost", 8888);
			//System.out.println("Olá "+apelido + " seja bem vindo!");
			byte[] b = null;
			Mensagem m = new Mensagem(TAG.INSERTAP, new Arquivo("foto.png", b), 4) ;
			Mensageiro sender = new Mensageiro(conexao, m);	
			sender.start();
			
		} catch (UnknownHostException e) {
			System.out.println("Não foi possível conectar-se!");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

}
