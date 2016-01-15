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
	
	public void carregarArquivos() throws IOException{ // Tem que ver como vai receber o List de objetos do Servidor
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		
		Mensagem mensagem = new Mensagem(TAG.SEEP,4);
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	
	public void uparArquivoemPasta(Arquivo arquivo, int idPastaRaiz) throws IOException{ // Quando não existir nenhuma pasta
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.INSERTAP,arquivo,idPastaRaiz); // Abstração/
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void uparArquivo(Arquivo arquivo, int idPasta) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.INSERTAP,arquivo,idPasta);
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void downloadArquivo(Arquivo arquivo) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.DOWNA,arquivo);
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem); 
	}
	
	public void compartilharArquivo(Arquivo arquivo,String userName) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.SHAREA,arquivo.getId(),userName); 
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
	    conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void compartilharPasta(int idPasta, String userName) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.SHAREP,idPasta,userName); 
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
	    conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void loginUsuario(Usuario usuario) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.LOGINU,usuario);
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		System.out.println(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	public void criarUsuario(Usuario usuario) throws IOException{
		ConnectionClient conexao = new ConnectionClient();
		conexao.openConnection("piratedropbox.com", 12345);
		Mensagem mensagem = new Mensagem(TAG.CREATEU,usuario);
		
		RetornaIp retornaIp = new RetornaIp();
		String ipCliente = retornaIp.retornaIp();
		mensagem.setIpCliente(ipCliente);
		
		conexao.enviarMensagemServidorDns(mensagem);
	}
	
	
}
