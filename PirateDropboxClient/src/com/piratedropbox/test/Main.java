package com.piratedropbox.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.piratedropbox.controller.ControladorRecebecimento;
import com.piratedropbox.controller.InterpreterMessage;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.view.CriarUsuario;
import com.piratedropbox.view.Login;
import com.piratedropbox.view.TelaInicialClient;

public class Main {
	public static TelaInicialClient telaInicialCliente = null;
	
	public static void main(String[] args) throws IOException {
		ControladorRecebecimento controladorRecebimento = new ControladorRecebecimento();
		//CriarUsuario criarUsuario = new CriarUsuario();
		//criarUsuario.setVisible(true);
		
		//Login login = new Login();
		//login.setVisible(true);
		
		
		
		
		telaInicialCliente = new TelaInicialClient();
	    telaInicialCliente.setVisible(true);
	    controladorRecebimento.recebeConexao();

	}
}
