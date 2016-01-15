package com.piratedropbox.controller;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.TAG;
import com.piratedropbox.model.Usuario;

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
			insertAP(msg);
			break;
		case TAG.INSERTPP:
			insertPP(msg);
			break;
		case TAG.CREATEP:
			createP(msg);
			break;
		case TAG.DOWNA:
			downA(msg);
			break;
		case TAG.SEEP :
			seeP(msg);
			break;
		case TAG.SHAREA:
			shareA(msg);
			break;
		case TAG.SHAREP:
			shareP(msg);
			break;
		case TAG.CREATEU:
			createU(msg);
			break;
		case TAG.LOGINU:
			loginU(msg);
			break;
		case TAG.SUCCESS:
			success(msg);
			break;
		case TAG.FAIL:
			fail(msg);
			break;
		}
	}
	public abstract void insertAP(Mensagem msg);
	public abstract void insertPP(Mensagem msg);
	public abstract void createP(Mensagem msg);
	public abstract void seeP(Mensagem msg);
	public abstract void downA(Mensagem msg);
	public abstract void shareA(Mensagem msg);
	public abstract void shareP(Mensagem msg);
	public abstract void createU(Mensagem msg);
	public abstract void loginU(Mensagem msg);
	public abstract void fail(Mensagem msg);
	public abstract void success(Mensagem msg);

}
