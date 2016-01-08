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
import com.piratedropbox.server.view.Mensageiro;

public class InterpreterMessage extends ActionByTag {
	
	private DBQueries db;
	
	public InterpreterMessage() {
		db = new DBQueries(ENDERECODB.localDB1, ENDERECODB.localDB2, "postgres", "senha");
	}
	
	@Override
	public void insertAP(Arquivo arquivo, int idPasta) {
		Mensagem resposta;
		if(db.insertAP(arquivo, idPasta)){
			resposta = new Mensagem(TAG.SUCCESS);
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
			resposta = new Mensagem(TAG.SUCCESS);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void seeP(int idPasta) {
		Mensagem resposta;
		List<Object> emPasta = db.seeP(idPasta);
		resposta = new Mensagem(TAG.SEEP, emPasta);
		sender(resposta);
	}

	@Override
	public void downA(int idArquivo) {
		Mensagem resposta;
		Arquivo arquivo = db.downA(idArquivo);
		resposta = new Mensagem(TAG.DOWNA, arquivo);
		sender(resposta);
	}

	@Override
	public void shareA(int idArquivo, String username) {
		Mensagem resposta;
		if(db.shareA(idArquivo, username)){
			resposta = new Mensagem(TAG.SUCCESS);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void shareP(int idPasta, String username) {
		Mensagem resposta;
		if(db.shareP(idPasta, username)){
			resposta = new Mensagem(TAG.SUCCESS);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void createU(Usuario usuario) {
		Mensagem resposta;
		if(db.createU(usuario)){
			resposta = new Mensagem(TAG.SUCCESS);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	@Override
	public void loginU(Usuario usuario) {
		Mensagem resposta;
		if(db.loginU(usuario)){
			resposta = new Mensagem(TAG.SUCCESS);
		}else{
			resposta = new Mensagem(TAG.FAIL);
		}
		sender(resposta);
	}

	private void sender(Mensagem resposta){
		try {
			Mensageiro m = new Mensageiro(new Socket(this.msg.getIpCliente(), 12345), resposta);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
