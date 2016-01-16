package com.piratedropbox.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import com.piratedropbox.controller.InterpreterMessage;
import com.piratedropbox.controller.Receiver;

public class ConnectionClient{
	private String host = "piratedropbox.com";
	private int porta = 12345;
	private String enderecoCliente;
	private Socket socket;
	private boolean clienteInicializado;
	private boolean clienteExecutando;
	private Thread thread;
	private BufferedReader in;
	private PrintStream out;
	
	
	public ConnectionClient(String host, int porta) throws IOException{
		clienteInicializado = false;
		clienteExecutando = false;
		openConnection(host,porta);
	}
	
	public ConnectionClient(){}
	
	public void openConnection(String host,int porta) throws IOException{
		try {
			System.out.println("Aidento");
//			socket = new Socket("piratedropbox.com",12345);
			socket = new Socket("127.0.0.1",23456);
			System.out.println(":P "+socket.toString());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			clienteInicializado = true;
			System.out.println(socket.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			socket.close();
		}
	}
	
	
	public void enviarMensagemServidorDns(Mensagem mensagem){ 
		Mensageiro mensageiro = new Mensageiro(this.socket,mensagem);
		mensageiro.start();
	}
	
	
	public boolean verificaExecucaoCliente(){
		return clienteExecutando;
	}
	

	
}
