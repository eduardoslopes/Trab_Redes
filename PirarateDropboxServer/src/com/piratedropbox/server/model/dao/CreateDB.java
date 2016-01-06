package com.piratedropbox.server.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	
	public static boolean createDataBase(String local, String user, String password) throws ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		String DB_URL = "jdbc:postgresql://"+local;
		String DB_NAME="bd_piratedropbox";
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL, user, password);
			Statement stmt = conn.createStatement();
			//stmt.executeUpdate("DROP DATABASE "+DB_NAME);
			String createDB = "CREATE DATABASE "+ DB_NAME;
			stmt.executeUpdate(createDB);
			conn = DriverManager.getConnection(DB_URL+DB_NAME, user, password);
			stmt = conn.createStatement();
			String createTables = 					
					"CREATE TABLE FOLDER( "+
					  "ID SERIAL, "+
					  "NAME VARCHAR(100) NOT NULL, "+
					  "PRIMARY KEY(ID) "+
					"); "+
					  
					"CREATE TABLE FILE( "+
					  "ID SERIAL, "+
					  "NAME VARCHAR(100) NOT NULL, "+
					  "BRUTE_FILE BYTEA, "+
					  "PRIMARY KEY(ID) "+
					"); "+
					
					"CREATE TABLE _USER( "+
					  "USERNAME VARCHAR(50) NOT NULL, "+
					  "NAME VARCHAR(20), "+
					  "LAST_NAME VARCHAR(60), "+
					  "SENHA VARCHAR(40), "+
					  "ID_ROOT_FOLDER INT NOT NULL, "+
					  "PRIMARY KEY(USERNAME), "+
					  "CONSTRAINT FK_PASTA_RAIZ FOREIGN KEY(ID_ROOT_FOLDER) REFERENCES FOLDER(ID) "+
					"); "+
					
					"CREATE TABLE FOLDER_FOLDER( "+
					  "ID_FOLDER_FATHER INT, "+
					  "ID_FOLDER_DAUGHTER INT, "+
					  "CONSTRAINT FK_FOLDER_FATHER FOREIGN KEY(ID_FOLDER_FATHER) REFERENCES FOLDER(ID), "+
					  "CONSTRAINT FK_FOLDER_DAUGHTER FOREIGN KEY(ID_FOLDER_DAUGHTER) REFERENCES FOLDER(ID) "+
					"); "+
					
					"CREATE TABLE FOLDER_FILE( "+
					  "ID_FOLDER_FATHER INT, "+
					  "ID_FILE INT, "+
					  "CONSTRAINT FK_FOLDER_FATHER FOREIGN KEY(ID_FOLDER_FATHER) REFERENCES FOLDER(ID), "+
					  "CONSTRAINT FK_FILE FOREIGN KEY(ID_FILE) REFERENCES FILE(ID) "+
					"); ";
			stmt.executeUpdate(createTables);
			stmt.close();
			conn.close();
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}

}
