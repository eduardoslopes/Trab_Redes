package com.piratedropbox.server.model;

public class Action extends ActionByTag {
	
	private Mensagem msg;
	
	public Action(Mensagem msg) {
		this.msg = msg;
	}
	
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
	public void seeAP(int idPasta) {
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
