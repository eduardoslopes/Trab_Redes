package com.piratedropbox.controller;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.ConnectionClient;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.TAG;

public class ControladorMensagemInterfaceGrafica {
	
	public ControladorMensagemInterfaceGrafica(){} 
	
	public void carregarArquivos(){ // Tem que ver como vai receber o List de objetos do Servidor
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem(TAG.SEEP);
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void uparPasta(Pasta pasta){
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem("criar pasta"); // Abstração/
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void uparArquivo(Arquivo arquivo){
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem("criar arquivo"); // Abstração/
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	
}
