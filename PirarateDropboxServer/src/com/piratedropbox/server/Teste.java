package com.piratedropbox.server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.piratedropbox.server.controller.ServerController;
import com.piratedropbox.server.model.dao.CreateDB;

public class Teste {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
		//System.out.println( CreateDB.createDataBase("localhost/", "postgres", "senha"));
		
//		Class.forName("org.postgresql.Driver");
//	    Connection conn = DriverManager.getConnection("jdbc:postgresql:bd_piratedropbox_teste", "postgres", "senha");
	    
//	    File file = new File("files/isac.png");
//	    FileInputStream fis = new FileInputStream(file);
//	    PreparedStatement pstmt = conn.prepareStatement("insert into arquivo(nome, arquivo_bruto) values (?,?)", Statement.RETURN_GENERATED_KEYS);
//	    pstmt.setString(1, "android2.png");
//	    pstmt.setBinaryStream(2, fis, (int) file.length());
//	    int check = pstmt.executeUpdate();
//	    ResultSet rs = pstmt.getGeneratedKeys();
//	    rs.next();
//	    System.out.println("pegou "+ rs.getString(2));
	    
//	    Statement stmt = conn.createStatement();
//	    ResultSet rs = stmt.executeQuery("select * from arquivo");
//	    while(rs.next()){
//		    byte[] b = rs.getBytes("arquivo_bruto");
//		    String nome = rs.getString("nome");
//		    System.out.println(b[0]);
//		    File f = new File(nome);
//		    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
//		    bos.write(b);
//		    bos.close();
//	    }
	    

//		ServerController sc1 = new ServerController(12345);
//		sc1.executa();
		
		ServerController sc2 = new ServerController(23456);
		sc2.executa();
		
	}

}
