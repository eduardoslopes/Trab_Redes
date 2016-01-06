package com.piratedropbox.server.model;

//TAG      |        UC
//---------|---------------------------------------|
//INSERTAP | Inserir um arquivo em uma pasta       |
//INSERTPP | Inserir uma pasta em uma pasta        |
//CREATEP  | Criar uma pasta                       |
//SEEAP    | Ver arquivos e pastas de uma pasta    |
//DOWNA    | Baixar um arquivo                     |
//SHAREA   | Compartilhar um arquivo               |
//SHAREP   | Compartilhar uma pasta                |


public interface ActionByTag {
	
	void messageInterpreter(Mensagem msg);
	void insertAP(Arquivo file, int idPasta);
	void insertPP(Pasta folder, int idPasta);
	void createP(Pasta folder);
	void seeAP(int id);
	void downA();
	void shareA();
	void shareP();

}
