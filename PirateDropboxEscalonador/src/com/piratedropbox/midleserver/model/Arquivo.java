package com.piratedropbox.midleserver.model;

public class Arquivo {
	private int id;
	private String nome;
	private byte[] arquivoBruto;
	
	public Arquivo(String nome, byte[] arquivoBruto){
		this.nome = nome;
		this.arquivoBruto = arquivoBruto;
	}
	
	
	
}
