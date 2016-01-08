package com.piratedropbox.server.controller;

import com.piratedropbox.server.model.ActionByTag;
import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;

public class InterpreterMessage extends ActionByTag {
	
	private Mensagem msg;

	@Override
	public void insertAP(Arquivo arquivo, int idPasta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPP(Pasta pasta, int idPasta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createP(Pasta pasta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seeP(int idPasta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downA(int idArquivo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shareA(int idArquivo, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shareP(int idPasta, String username) {
		// TODO Auto-generated method stub
		
	}
	

}
