package com.piratedropbox.server.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import com.piratedropbox.server.controller.ServerController;

public class Main {

public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
//		ServerController sc1 = new ServerController(12345);
//		sc1.executa();
		
		ServerController sc2 = new ServerController(23456);
		sc2.executa();
		
	}

}
