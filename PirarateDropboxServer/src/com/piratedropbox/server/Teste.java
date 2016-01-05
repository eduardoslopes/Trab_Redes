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

public class Teste {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		Class.forName("org.postgresql.Driver");
	    Connection conn = DriverManager.getConnection("jdbc:postgresql:bd_piratedropbox_teste", "postgres", "senha");
//	    File file = new File("files/isac.png");
//	    FileInputStream fis = new FileInputStream(file);
//	    PreparedStatement pstmt = conn.prepareStatement("insert into arquivo(id, nome, arquivo_bruto) values (?,?,?)");
//	    pstmt.setInt(1, 1);
//	    pstmt.setString(2, "Teste saporra!");
//	    pstmt.setBinaryStream(3, fis, (int) file.length());
//	    int check = pstmt.executeUpdate();
//	    System.out.println(check);
	    
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery("select * from arquivo");
	    while(rs.next()){
		    byte[] b = rs.getBytes("arquivo_bruto");
		    System.out.println(b[0]);
		    File f = new File("files/isac2");
		    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
		    bos.write(b);
		    bos.close();
	    }
	    
	    Socket s = new Socket("127.0.0.1", 8888);
	    
	}

}
