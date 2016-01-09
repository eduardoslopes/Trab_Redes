package com.piratedropbox.model;

public class Pasta {
	private int id;
	private int idPai;
	private String nome;
	
	public Pasta(String nome, int idPai) {
		this.nome = nome;
		this.idPai = idPai;
	}

	public Pasta(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public int getIdPai() {
		return idPai;
	}

	public String getNome() {
		return nome;
	}
	
}
