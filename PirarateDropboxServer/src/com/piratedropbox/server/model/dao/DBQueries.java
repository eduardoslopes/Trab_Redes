package com.piratedropbox.server.model.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.piratedropbox.server.controller.ENDERECODB;
import com.piratedropbox.server.model.Arquivo;
import com.piratedropbox.server.model.Pasta;
import com.piratedropbox.server.model.Usuario;

public class DBQueries {
	
	private String DB1_URL = "jdbc:postgresql://";
	private String DB2_URL = "jdbc:postgresql://";
	private Connection conn1;
	private Connection conn2;
	private String user;
	private String password;
	private static final String dbName1 = "db1_piratedropbox";
	private static final String dbName2 = "db2_piratedropbox";
	private Statement stmt1;
	private Statement stmt2;
	
	public DBQueries(String localDB1, String localDB2, String user, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		DB1_URL = "jdbc:postgresql://"+localDB1+dbName1;
		DB2_URL = "jdbc:postgresql://"+localDB2+dbName2;
		
		this.user = user;
		this.password = password;
				
		try {
			conn1 = DriverManager.getConnection(DB1_URL, user, password);
			stmt1 = conn1.createStatement();
		} catch (SQLException e) {
			try {
				CreateDB.createDataBase(ENDERECODB.localDB1, dbName1, user, password);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}try {
			conn2 = DriverManager.getConnection(DB2_URL, user, password);
			stmt2 = conn2.createStatement();
		} catch (SQLException e) {
			try {
				CreateDB.createDataBase(ENDERECODB.localDB2, dbName2, user, password);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	public void insertAP(Arquivo arquivo, int idPasta) {
	    try {
			PreparedStatement pstmt = conn1.prepareStatement("insert into file(name, BRUTE_FILE) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, arquivo.getNome());
			pstmt.setBytes(2, arquivo.getArquivoBruto());
			int check = pstmt.executeUpdate();
			ResultSet newKey = pstmt.getGeneratedKeys();
			newKey.next();
			int newId = newKey.getInt(1);
			pstmt = conn1.prepareStatement("insert into folder_file(ID_FOLDER_FATHER, ID_FILE) values (?,?)");
			pstmt.setInt(1, idPasta);
			pstmt.setInt(2, newId);
			check = pstmt.executeUpdate();
			
			pstmt = conn2.prepareStatement("insert into file(name, BRUTE_FILE) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, arquivo.getNome());
			pstmt.setBytes(2, arquivo.getArquivoBruto());
			check = pstmt.executeUpdate();
			newKey = pstmt.getGeneratedKeys();
			newKey.next();
			newId = newKey.getInt(1);
			pstmt = conn2.prepareStatement("insert into folder_file(ID_FOLDER_FATHER, ID_FILE) values (?,?)");
			pstmt.setInt(1, idPasta);
			pstmt.setInt(2, newId);
			check = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertPP(Pasta pasta, int idPasta) {
		// TODO Auto-generated method stub
		
	}

	public void createP(Pasta pasta) {
		try {
			PreparedStatement pstmt = conn1.prepareStatement("insert into folder(name) values (?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pasta.getNome());
			int check = pstmt.executeUpdate();
			ResultSet newKey = pstmt.getGeneratedKeys();
			newKey.next();
			int newId = newKey.getInt(1);
			pstmt = conn1.prepareStatement("insert into folder_folder(ID_FOLDER_FATHER, ID_FOLDER_DAUGHTER) values (?,?)");
			pstmt.setInt(1, pasta.getIdPai());
			pstmt.setInt(2, newId);
			check = pstmt.executeUpdate();
			
			pstmt = conn2.prepareStatement("insert into folder(name) values (?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pasta.getNome());
			check = pstmt.executeUpdate();
			newKey = pstmt.getGeneratedKeys();
			newKey.next();
			newId = newKey.getInt(1);
			pstmt = conn2.prepareStatement("insert into folder_folder(ID_FOLDER_FATHER, ID_FOLDER_DAUGHTER) values (?,?)");
			pstmt.setInt(1, pasta.getIdPai());
			pstmt.setInt(2, newId);
			check = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Object> seeP(int idPasta) {
		List<Object> intoFolder = new ArrayList<>();
			Statement stmt = null;
			try {
				stmt = conn1.createStatement();
			} catch (SQLException e1) {
				try {
					stmt = conn2.createStatement();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
		try {
		    ResultSet rs = stmt.executeQuery("select f.id, f.name from file f, folder fd, FOLDER_FILE ff "
		    		+ "where f.id = ff.id_file and ff.ID_FOLDER_FATHER = fd.id and fd.id = "+idPasta+" ;");
		    while(rs.next()){
			    int id = rs.getInt("id");
				String nome = rs.getString("name");
				intoFolder.add(new Arquivo(id, nome));
		    }
		    stmt = conn1.createStatement();
		    rs = stmt.executeQuery("select fd.id, fd.name folder fd, folder_folder ff "
		    		+ "where "+idPasta+" = ff.ID_FOLDER_FATHER and ff.ID_FOLDER_FATHER = fd.id "
		    				+ "and fd.id = ff.ID_FOLDER_DAUGHTER;");
		    while(rs.next()){
			    int id = rs.getInt("id");
				String nome = rs.getString("name");
				intoFolder.add(new Pasta(id, nome));
		    }	  
	    } catch (SQLException e) {
			e.printStackTrace();
		}   
		return intoFolder;
	}

	public Arquivo downA(int idArquivo) {
		Statement stmt = null;
		Arquivo arquivo = null;
		try {
			stmt = conn1.createStatement();
		} catch (SQLException e1) {
			try {
				stmt = conn2.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		try {
		    ResultSet rs = stmt.executeQuery("select name, BRUTE_FILE file where id = "+idArquivo);
		    while(rs.next()){
				String nome = rs.getString("name");
				byte[] arquivoBruto = rs.getBytes("BRUTE_FILE");
				arquivo = new Arquivo(nome, arquivoBruto);
		    }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return arquivo;
	}

	public void shareA(int idArquivo, String username) {
		try {
			Statement stmt = conn1.createStatement();
			ResultSet rs = stmt.executeQuery("select ID_ROOT_FOLDER from _user where USERNAME = "+username);
			rs.next();
			int idRaiz = rs.getInt("ID_ROOT_FOLDER");
			PreparedStatement pstmt = conn1.prepareStatement("insert into folder_file(ID_FOLDER_FATHER, ID_FILE) values (?,?)");
			pstmt.setInt(1, idRaiz);
			pstmt.setInt(2, idArquivo);
			pstmt.executeUpdate();
			
			stmt = conn2.createStatement();
			rs = stmt.executeQuery("select ID_ROOT_FOLDER from _user where USERNAME = "+username);
			rs.next();
			idRaiz = rs.getInt("ID_ROOT_FOLDER");
			pstmt = conn2.prepareStatement("insert into folder_file(ID_FOLDER_FATHER, ID_FILE) values (?,?)");
			pstmt.setInt(1, idRaiz);
			pstmt.setInt(2, idArquivo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void shareP(int idPasta, String username) {
		try {
			Statement stmt = conn1.createStatement();
			ResultSet rs = stmt.executeQuery("select ID_ROOT_FOLDER from _user where USERNAME = "+username);
			rs.next();
			int idRaiz = rs.getInt("ID_ROOT_FOLDER");
			PreparedStatement pstmt = conn1.prepareStatement("insert into folder_folder(ID_FOLDER_FATHER, ID_FOLDER_DAUGHTER) "
					+ "values (?,?)");
			pstmt.setInt(1, idRaiz);
			pstmt.setInt(2, idPasta);
			pstmt.executeUpdate();
			
			stmt = conn1.createStatement();
			rs = stmt.executeQuery("select ID_ROOT_FOLDER from _user where USERNAME = "+username);
			rs.next();
			idRaiz = rs.getInt("ID_ROOT_FOLDER");
			pstmt = conn1.prepareStatement("insert into folder_folder(ID_FOLDER_FATHER, ID_FOLDER_DAUGHTER) "
					+ "values (?,?)");
			pstmt.setInt(1, idRaiz);
			pstmt.setInt(2, idPasta);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	public void createU(Usuario usuario) {
		PreparedStatement pstmt;
		try {
			pstmt = conn1.prepareStatement("insert into folder(name) "
					+ "values (?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "Home_"+usuario.getNome());
			pstmt.executeUpdate();
			ResultSet newKey = pstmt.getGeneratedKeys();
			newKey.next();
			int newId = newKey.getInt(1);
			pstmt = conn1.prepareStatement("insert into _user(username, name, last_name, senha, id_root_folder) "
					+ "values(?, ?, ?, ?, ?);");
			pstmt.setString(1, usuario.getUsername());
			pstmt.setString(2, usuario.getNome());
			pstmt.setString(3, usuario.getSobrenome());
			pstmt.setString(4, usuario.getSenha());
			pstmt.setInt(5, newId);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt = conn2.prepareStatement("insert into folder(name) "
					+ "values (?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "Home_"+usuario.getNome());
			pstmt.executeUpdate();
			ResultSet newKey = pstmt.getGeneratedKeys();
			newKey.next();
			int newId = newKey.getInt(1);
			pstmt = conn2.prepareStatement("insert into _user(username, name, last_name, senha, id_root_folder) "
					+ "values(?, ?, ?, ?, ?);");
			pstmt.setString(1, usuario.getUsername());
			pstmt.setString(2, usuario.getNome());
			pstmt.setString(3, usuario.getSobrenome());
			pstmt.setString(4, usuario.getSenha());
			pstmt.setInt(5, newId);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean loginU(Usuario usuario) {
		
		Statement stmt = null;
		String username = null ;
		String password = null ;
		try {
			try{
			stmt = conn1.createStatement();
			}catch(SQLException e2){
				stmt = conn2.createStatement();
				ResultSet rs = stmt.executeQuery("select username, password from _user where USERNAME = "+usuario.getUsername());
				rs.next();
				username = rs.getString("username");
				password = rs.getString("password");
				if(username.equals(usuario.getUsername()) && password.equals(usuario.getSenha())){
					return true;
				}
			}
			ResultSet rs = stmt.executeQuery("select username, password from _user where USERNAME = "+usuario.getUsername());
			rs.next();
			username = rs.getString("username");
			password = rs.getString("password");
			if(username.equals(usuario.getUsername()) && password.equals(usuario.getSenha())){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
