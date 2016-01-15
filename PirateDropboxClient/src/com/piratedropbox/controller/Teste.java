package com.piratedropbox.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Teste {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 9999);
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println(" :P ");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
