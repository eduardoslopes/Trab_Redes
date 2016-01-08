package com.piratedropbox.server.controller;

import java.util.List;

import com.piratedropbox.server.model.ActionByTag;
import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.Usuario;
import com.piratedropbox.server.model.dao.DBQueries;

public class InterpreterMessage extends ActionByTag {
	
	private DBQueries db;
	
	public InterpreterMessage() {
		db = new DBQueries(ENDERECODB.localDB1, ENDERECODB.localDB2, "postgres", "senha");
	}
	
	@Override
	public void insertAP(Arquivo arquivo, int idPasta) {
		db.insertAP(arquivo, idPasta);
	}

	@Override
	public void insertPP(Pasta pasta, int idPasta) {
		db.insertPP(pasta, idPasta);
	}

	@Override
	public void createP(Pasta pasta) {
		db.createP(pasta);
	}

	@Override
	public List<Object> seeP(int idPasta) {
		db.seeP(idPasta);
		return db.seeP(idPasta);
	}

	@Override
	public Arquivo downA(int idArquivo) {
		return db.downA(idArquivo);
	}

	@Override
	public void shareA(int idArquivo, String username) {
		db.shareA(idArquivo, username);
	}

	@Override
	public void shareP(int idPasta, String username) {
		db.shareP(idPasta, username);
	}

	@Override
	public void createU(Usuario usuario) {
		db.createU(usuario);
	}

	@Override
	public void loginU(Usuario usuario) {
		db.loginU(usuario);
	}

	

}
