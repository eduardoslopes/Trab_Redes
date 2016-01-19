package com.piratedropbox.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.TAG;
import com.piratedropbox.test.Main;

public class ControladorRecebecimento { // extends Thread{
	private ServerSocket server;

	public ControladorRecebecimento() throws IOException {
		server = new ServerSocket(9999);
	}

	public void recebeConexao() throws IOException {

		while (true) {
			Socket socketCliente = server.accept();
			Scanner scanner;
			InterpreterMessage im = new InterpreterMessage();

			try {
				scanner = new Scanner(socketCliente.getInputStream());
				String msg = null;
				System.out.println("1");

				msg = scanner.nextLine();
				Mensagem m = Mensagem.jsonToMensagem(msg);

				List<Object> obj = null;
				if (m.getTAG().equals(TAG.SEEP)) {
					obj = new ArrayList<Object>();
					if (m.getArquivo() == null){
						if (m.getPasta() != null){
							obj.add(m.getPasta());
						}
					}else{
						obj.add(m.getArquivo());
					}
					while (scanner.hasNextLine()) {

						msg = scanner.nextLine();

						m = Mensagem.jsonToMensagem(msg);
						if (m.getTAG().equals(TAG.SEEP)) {
							if (m.getArquivo() == null) {
								if (m.getPasta() != null)
									obj.add(m.getPasta());
							} else
								obj.add(m.getArquivo());
						}

						else
							break;
					}
					Main.telaInicialCliente.carregarObjetos(obj);
				} else {
					im.messageInterpreter(m);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
