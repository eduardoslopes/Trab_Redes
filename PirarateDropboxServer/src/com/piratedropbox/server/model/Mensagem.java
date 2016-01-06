package com.piratedropbox.server.model;

import com.google.gson.Gson;

public class Mensagem {
	private String TAG;
	private Object object;


	public static String mensagemToJson(Mensagem msg){
		Gson gson = new Gson();
		return gson.toJson(msg);
	}
	
	public static Mensagem jsonToMensagem(String jsonMsg){
		Gson gson = new Gson();
		Mensagem msg = gson.fromJson(jsonMsg, Mensagem.class);
		return msg;
	}
}