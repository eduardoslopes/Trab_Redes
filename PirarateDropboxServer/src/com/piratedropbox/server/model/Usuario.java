package com.piratedropbox.server.model;

public class Usuario {
	private String username;
	private String nome;
	private String sobrenome;
	private String senha;
	private int idPastaRaiz;
	
	public Usuario() {}
	
	public Usuario(String username, String senha, String nome, String sobrenome, int idPastaRaiz){
		this.username = username;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idPastaRaiz = idPastaRaiz;
	}
	
}
