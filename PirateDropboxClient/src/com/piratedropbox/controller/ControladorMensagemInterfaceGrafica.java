package com.piratedropbox.controller;

import java.io.IOException;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.ConnectionClient;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.RetornaIp;
import com.piratedropbox.model.TAG;
import com.piratedropbox.model.Usuario;

public class ControladorMensagemInterfaceGrafica {
	
	public ControladorMensagemInterfaceGrafica(){} 
	
	public void carregarArquivos(int idPasta) { 
		Mensagem mensagem = new Mensagem(TAG.SEEP,idPasta);
		sender(mensagem);
	}
	
	
	public void uparArquivoemPasta(Arquivo arquivo, int idPastaRaiz) {
		Mensagem mensagem = new Mensagem(TAG.INSERTAP,arquivo,idPastaRaiz); // Abstração/
		sender(mensagem);
	}
	
	public void uparArquivo(Arquivo arquivo, int idPasta) {
		Mensagem mensagem = new Mensagem(TAG.INSERTAP,arquivo,idPasta);
		sender(mensagem);
	}
	
	public void downloadArquivo(Arquivo arquivo) {
		Mensagem mensagem = new Mensagem(TAG.DOWNA,arquivo);
		sender(mensagem);
	}
	
	public void compartilharArquivo(Arquivo arquivo,String userName) {
		Mensagem mensagem = new Mensagem(TAG.SHAREA,arquivo.getId(),userName); 
		sender(mensagem);
	}
	
	public void compartilharPasta(int idPasta, String userName){
		Mensagem mensagem = new Mensagem(TAG.SHAREP,idPasta,userName); 
		sender(mensagem);
	}
	
	public void loginUsuario(Usuario usuario) {
		Mensagem mensagem = new Mensagem(TAG.LOGINU,usuario);
		sender(mensagem);
	}
	
	public void criarUsuario(Usuario usuario){
		Mensagem mensagem = new Mensagem(TAG.CREATEU,usuario);
		sender(mensagem);
	}
	
	public void criarPasta(Pasta pasta){
		Mensagem mensagem = new Mensagem(TAG.CREATEP,pasta);
		sender(mensagem);
	}
	
	private void sender(Mensagem mensagem){
		ConnectionClient conexao = new ConnectionClient();
		try {
//			conexao.openConnection("piratedropbox.com", 12345);
			conexao.openConnection("127.0.0.1", 23456);
		} catch (IOException e) {
			e.printStackTrace();
		}
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	
}
