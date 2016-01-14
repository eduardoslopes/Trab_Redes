package com.piratedropbox.server.controller;

import java.util.ArrayList;
import java.util.List;

public class VerificadorTrafego {
	
	public static int verificaTrafego(){
		List<Thread> delete = new ArrayList<>();
		int cont = 0;
		for(Thread t : ServerController.connections){
			if(t.isAlive()){
				cont++;
			}else{
				delete.add(t);
			}
		}
		for(Thread r : delete){
			ServerController.connections.remove(r);
		}
		return cont;
	}

}
