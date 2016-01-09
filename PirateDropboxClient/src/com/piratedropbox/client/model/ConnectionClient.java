package com.piratedropbox.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionClient implements Runnable{
	private String host;  //"piratedropbox.com";
	private int porta;  //"12345";
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
			socket = new Socket("piratedropbox.com",12345);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			clienteInicializado = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			socket.close();
		}
	}
	
	private void close(){
		if(in != null){
			try{
				in.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		if(out != null){
			try{
				out.close();
				System.setOut(out);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		if(socket != null){
			try{
				socket.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		in = null;
		out = null;
		socket = null;
		clienteInicializado = false;
		clienteExecutando = false;
		thread.interrupt();
	}
	
	private void start(){ // Iniciar Thread auxiliar, se poss�vel. O objeto Cliente precisa estar inicializado e n�o pode estar executando
	  	if(!clienteInicializado || clienteExecutando){
	  		return;
	  	}
	  	
	  	clienteExecutando = true;
	  	thread = new Thread(this);
	  	thread.start();
	}
	
	public void stop() throws Exception{
		clienteExecutando = false;
		if(thread != null){
			thread.join();
		}
	}
	
	
	
	@Override
	public void run() {
		while(clienteExecutando){ // Esperando resultado do servidor
			try{
				
				String mensagemServidor = in.readLine();
				
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		close();
		
	}
	
	private void enviarMensagemServidorDns(Mensagem mensagem){ 
		Mensageiro mensageiro = new Mensageiro(this.socket,mensagem);
		mensageiro.enviarMensagem();
	}
	
	
	public boolean verificaExecucaoCliente(){
		return clienteExecutando;
	}
	
	
}
