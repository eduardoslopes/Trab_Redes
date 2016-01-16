package com.piratedropbox.model;

public class Usuario {
	private String username;
	private String nome;
	private String sobrenome;
	private String senha;
	private int idPastaRaiz;
		
	public Usuario(String username, String senha, String nome, String sobrenome, int idPastaRaiz){
		this.username = username;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idPastaRaiz = idPastaRaiz;
	}
	
	public Usuario(String username, String senha, String nome, String sobrenome){
		this.username = username;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public Usuario(String userName2, String senha2) {
		this.username = userName2;
		this.senha = senha2;
	}

	public String getUsername() {
		return username;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public int getIdPastaRaiz() {
		return idPastaRaiz;
	}
	
}
