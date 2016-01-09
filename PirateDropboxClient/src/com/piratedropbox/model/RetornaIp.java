package com.piratedropbox.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class RetornaIp {
	public String retornaIp(){
		
		Enumeration nis = null;
		String ip = null;
		String nome = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (nis.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) nis.nextElement();
            Enumeration ias = ni.getInetAddresses();
            InetAddress ia;
            while (ias.hasMoreElements()) {
                ia = (InetAddress) ias.nextElement();
                if (ia.getHostAddress().contains("10.0")) {//Nesse if está a charada, sendo que eu sei que meu ip começa com 10.132, por exemplo
                	ip = ia.getHostAddress();    
                }
             
                if (!ni.getName().equals("lo")) {
                   // System.out.println(ia.getHostAddress());
                }
            }
        }
        return ip;
	}
}



