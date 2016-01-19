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
		criarUsuario = new CriarUsuario();
		criarUsuario.setVisible(false);
		
		
		login = new Login();
		login.setVisible(true);

		telaInicialCliente = new TelaInicialClient();
		ControladorRecebecimento controladorRecebimento = new ControladorRecebecimento();
		controladorRecebimento.recebeConexao();
	}
}
