package com.piratedropbox.model;

public class Arquivo {
	private int id;
	private String nome;
	private byte[] arquivoBruto;
	
	public Arquivo(String nome, byte[] arquivoBruto){
		this.nome = nome;
		this.arquivoBruto = arquivoBruto;
	}
	
	public Arquivo(int id, String nome){
		this.nome = nome;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public byte[] getArquivoBruto() {
		return arquivoBruto;
	}

		
}
