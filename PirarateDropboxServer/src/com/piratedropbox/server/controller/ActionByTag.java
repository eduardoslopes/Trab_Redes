package com.piratedropbox.server.controller;

import java.util.List;

import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Mensagem;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.TAG;
import com.piratedropbox.server.model.Usuario;

//TAG      |        UC
//---------|---------------------------------------|
//INSERTAP | Inserir um arquivo em uma pasta       |
//INSERTPP | Inserir uma pasta em uma pasta        |
//CREATEP  | Criar uma pasta                       |
//SEEAP    | Ver arquivos e pastas de uma pasta    |
//DOWNA    | Baixar um arquivo                     |
//SHAREA   | Compartilhar um arquivo               |
//SHAREP   | Compartilhar uma pasta                |


public abstract class ActionByTag {
	
	public Mensagem msg;
	
	public void messageInterpreter(Mensagem msg){
		this.msg = msg;
		switch(msg.getTAG()){
		case TAG.INSERTAP:
			insertAP(msg.getArquivo(), msg.getId());
			break;
		case TAG.INSERTPP:
			insertPP(msg.getPasta(), msg.getId());
			break;
		case TAG.CREATEP:
			createP(msg.getPasta());
			break;
		case TAG.DOWNA:
			downA(msg.getArquivo().getId());
			break;
		case TAG.SEEP :
			seeP(msg.getId());
			break;
		case TAG.SHAREA:
			shareA(msg.getId(), msg.getUsername());
			break;
		case TAG.SHAREP:
			shareP(msg.getId(), msg.getUsername());
			break;
		case TAG.CREATEU:
			createU(msg.getUsuario());
			break;
		case TAG.LOGINU:
			loginU(msg.getUsuario());
			break;
		}
	}
	public abstract void insertAP(Arquivo arquivo, int idPasta);
	public abstract void insertPP(Pasta pasta, int idPasta);
	public abstract void createP(Pasta pasta);
	public abstract void seeP(int idPasta);
	public abstract void downA(int idArquivo);
	public abstract void shareA(int idArquivo, String username);
	public abstract void shareP(int idPasta, String username);
	public abstract void createU(Usuario usuario);
	public abstract void loginU(Usuario usuario);

}
