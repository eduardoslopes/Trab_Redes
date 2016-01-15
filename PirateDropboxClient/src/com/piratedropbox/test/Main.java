package com.piratedropbox.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.piratedropbox.controller.ControladorRecebecimento;
import com.piratedropbox.controller.InterpreterMessage;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.view.CriarUsuario;
import com.piratedropbox.view.Login;
import com.piratedropbox.view.TelaInicialClient;

public class Main {
	public static TelaInicialClient telaInicialCliente = null;
	public static Login login = null;
	public static CriarUsuario criarUsuario = null;
	
	public static void main(String[] args) throws IOException {
		//Arquivo arquivo = new Arquivo(2,"Arquivo");
		//Map<Object, Icon> icons = new HashMap<Object, Icon>();
		//icons.put(ar
		
		//criarUsuario = new CriarUsuario();
		//criarUsuario.setVisible(true);
		
		
		login = new Login();
		login.setVisible(true);
//		if(login.podeLogar == true){
//			
//		}
	
		
		
		
	   telaInicialCliente = new TelaInicialClient();
	   //telaInicialCliente.setVisible(true);
	   ControladorRecebecimento controladorRecebimento = new ControladorRecebecimento();
	   controladorRecebimento.recebeConexao();
		
		///CriarUsuario criarUsuario = new CriarUsuario();
		//criarUsuario.setVisible(true);
		
		//Login login = new Login();
		//login.setVisible(true);
		
		
		
		
		
	    //controladorRecebimento.recebeConexao();

	}
}
