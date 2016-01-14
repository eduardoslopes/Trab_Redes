package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("192.168.0.105", 9999);
			PrintStream ps = new PrintStream(s.getOutputStream());
			ps.println("Buceta!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
