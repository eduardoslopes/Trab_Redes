package com.piratedropbox.server.model;

import java.util.List;

import com.google.gson.Gson;

public class Mensagem {
	private String TAG;
	private Arquivo arquivo;
	private Pasta pasta;
	private int id;
	private String username;
	private String ipCliente;
	private Usuario usuario;
	private List<Object> emPasta;

	public Mensagem(String tag, Arquivo arquivo, int idPasta){
		this.TAG = tag;
		this.arquivo = arquivo;
		this.id = idPasta;
	}
	
	public Mensagem(String tag, Arquivo arquivo){
		this.TAG = tag;
		this.arquivo = arquivo;
	}
	
	public Mensagem(String tag, Pasta pasta, int idPasta){
		this.TAG = tag;
		this.pasta = pasta;
		this.id = idPasta;
	}
	
	public Mensagem(String tag, Pasta pasta){
		this.TAG = tag;
		this.pasta = pasta;
	}
	
	public Mensagem(String tag, int id){
		this.TAG = tag;
		this.id = id;
	}
	
	public Mensagem(String tag, int id, String username){
		this.TAG = tag;
		this.id = id;
		this.username = username;
	}
	
	public Mensagem(String tag, Usuario usuario){
		this.TAG = tag;
		this.usuario = usuario;
	}
	
	public Mensagem(String tag){
		this.TAG = tag;
	}
	
	public Mensagem(String tag, List<Object> emPasta){
		this.TAG = tag;
		this.emPasta = emPasta;
	}
	
	

	public static String mensagemToJson(Mensagem msg){
		Gson gson = new Gson();
		return gson.toJson(msg);
	}
	
	public static Mensagem jsonToMensagem(String jsonMsg){
//		System.out.println("msgem: " + jsonMsg);
		Gson gson = new Gson();
		Mensagem msg = gson.fromJson(jsonMsg, Mensagem.class);
		return msg;
	}
	
	public List<Object> getEmPasta() {
		return emPasta;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getTAG() {
		return TAG;
	}

}