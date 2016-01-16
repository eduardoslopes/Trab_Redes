package com.piratedropbox.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.TAG;
import com.piratedropbox.model.Usuario;
import com.piratedropbox.test.Main;
import com.piratedropbox.view.Login;
import com.piratedropbox.view.TelaInicialClient;

public class InterpreterMessage extends ActionByTag {
	
	@Override
	public void insertAP(Mensagem msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertPP(Mensagem msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createP(Mensagem msg) {
		if(msg.getTAG().equals(TAG.CREATEP)){
			JOptionPane.showMessageDialog(null, "Pasta criada com sucesso!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Falha ao criar pasta!");
		}
		
	}

	@Override
	public void seeP(Mensagem msg) {
		if(msg.getTAG().equals(TAG.SEEP)){
			List<Object> arquivos = new ArrayList<Object>();
			System.out.println(((Arquivo)msg.getEmPasta().get(0)).getNome() ); 
				Main.telaInicialCliente.carregarObjetos(arquivos);
		}
		
	}

	@Override
	public void downA(Mensagem msg) {
		if(msg.getTAG().equals(TAG.DOWNA)){
			FileInputStream fileInputStream;
			
			try {
				//JFileChooser arquivoSeleciona = new JFileChooser(); 
				//arquivoSeleciona.setDialogTitle("Abrir Arquivo");
				//arquivoSeleciona.getCurrentDirectory();
				//arquivoSeleciona.showOpenDialog(null);
				//File file = arquivoSeleciona.getSelectedFile();
				//Path path = Paths.get(file.getPath());
				
				
			byte[] ArquivoBytes = msg.getArquivo().getArquivoBruto();
			File file = new File("/home/julioserafim/Downloads/" + msg.getArquivo().getNome());
			FileOutputStream fileOuputStream = new FileOutputStream(file);
			BufferedOutputStream buffer = new BufferedOutputStream(fileOuputStream);
		    //Converte array de bytes em file
		     buffer.write(ArquivoBytes);
		     buffer.close();
		    //fileOuputStream.write(ArquivoBytes);
		   // fileOuputStream.close();
		    
			}catch(Exception e){
	            e.printStackTrace();
	        }
			
		}
		
		else if(msg.getArquivo() == null){
			JOptionPane.showMessageDialog(null, "Falha na operação de Download");
		}
		
	}

	@Override
	public void shareA(Mensagem msg) {
		if(msg.getTAG().equals(TAG.SHAREA)){
			JOptionPane.showMessageDialog(null, "Arquivo Compartilhado");
		}
		
	}

	@Override
	public void shareP(Mensagem msg) {
		if(msg.getTAG().equals(TAG.SHAREP)){
			JOptionPane.showMessageDialog(null, "Pasta Compartilhada");
		}
		
	}

	@Override
	public void createU(Mensagem msg) {
		if(msg.getTAG().equals(TAG.CREATEU)){
			JOptionPane.showMessageDialog(null, "Usuário criado com sucesso");
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Falha ao criar usuário");
		}
		
	}

	@Override
	public void loginU(Mensagem msg) {
		if(msg.getTAG().equals(TAG.LOGINU)){
			Login login = new Login();
			login.setPodeLogar(true);
			System.out.println("mensagem" + msg.getId());
			ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
			try {
				controlador.carregarArquivos(msg.getPasta().getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.telaInicialCliente.setVisible(true);
			Main.telaInicialCliente.setPasta(msg.getPasta().getId());
//			telaCliente.pack();
//			telaCliente.setVisible(true);
			
		}
		
		// msg.getPasta();
		
		else{
			JOptionPane.showMessageDialog(null, "Falha na operação de login");
		}

	}

	@Override
	public void fail(Mensagem msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(Mensagem msg) {
		// TODO Auto-generated method stub
		
	}


}
