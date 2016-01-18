package com.piratedropbox.server.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.piratedropbox.server.model.ActionByTag;
import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.TAG;
import com.piratedropbox.server.model.Usuario;
import com.piratedropbox.server.model.dao.DBQueries;

public class InterpreterMessage extends ActionByTag {
	
	private DBQueries db;
	
	public InterpreterMessage() {
		db = new DBQueries(ENDERECODB.localDB1, ENDERECODB.localDB2, "postgres", "senha");
	}
	
	@Override
	public void insertAP(Arquivo arquivo, int idPasta) {
		Mensagem resposta;
		if(db.insertAP(arquivo, idPasta)){
			resposta = new Mensagem(TAG.INSERTAP);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void insertPP(Pasta pasta, int idPasta) {

	}

	@Override
	public void createP(Pasta pasta) {
		Mensagem resposta;
		if(db.createP(pasta)){
			resposta = new Mensagem(TAG.CREATEP);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void seeP(int idPasta) {
		Mensagem resposta;
		List<Object> emPasta = db.seeP(idPasta);
		sender(emPasta);
	}

	@Override
	public void downA(int idArquivo) {
		Mensagem resposta;
		Arquivo arquivo = db.downA(idArquivo);
		System.out.println(arquivo == null);
		System.out.println("Nome: "+arquivo.getNome()+ " bytes: "+ arquivo.getArquivoBruto());
		resposta = new Mensagem(TAG.DOWNA, arquivo);
		sender(resposta);
	}

	@Override
	public void shareA(int idArquivo, String username) {
		Mensagem resposta;
		if(db.shareA(idArquivo, username)){
			resposta = new Mensagem(TAG.SHAREA);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void shareP(int idPasta, String username) {
		Mensagem resposta;
		if(db.shareP(idPasta, username)){
			resposta = new Mensagem(TAG.SHAREP);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void createU(Usuario usuario) {
		Mensagem resposta;
		if(db.createU(usuario)){
			resposta = new Mensagem(TAG.CREATEU);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void loginU(Usuario usuario) {
		System.out.println("Nulo? "+usuario == null);
		System.out.println("buceta do caralho!");
		Mensagem resposta;
		Pasta pastaRaiz = db.loginU(usuario);
		if(pastaRaiz != null){
			resposta = new Mensagem(TAG.LOGINU, pastaRaiz);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	private void sender(Mensagem resposta){
		try {
			System.out.println( "IP: "+ this.msg.getIpCliente());
			Socket sock = new Socket(this.msg.getIpCliente(), 9999);
			Mensageiro m = new Mensageiro(sock, resposta);
			m.start();
			ServerController.connections.add(m);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sender(List<Object> emPasta){
		try {
			System.out.println( "IP: "+ this.msg.getIpCliente());
			Socket sock = new Socket(this.msg.getIpCliente(), 9999);
			MensageiroDePasta m = new MensageiroDePasta(sock, emPasta);
			m.start();
			ServerController.connections.add(m);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
