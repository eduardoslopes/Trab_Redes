package com.piratedropbox.controller;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.ConnectionClient;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;

public class ControladorMensagemInterfaceGrafica {
	
	public ControladorMensagemInterfaceGrafica(){} // Mandar o id
	
	public void carregarArquivos(){
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem(TAG.SEEP);
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void uparPasta(Pasta pasta){
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem("criar pasta"); // Abstração
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void uparArquivo(Arquivo arquivo){
		ConnectionClient conexao = new ConnectionClient();
		Mensagem mensagem = new Mensagem("criar arquivo"); // Abstração
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	
}
