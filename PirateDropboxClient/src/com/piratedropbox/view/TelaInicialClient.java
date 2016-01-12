package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.piratedropbox.controller.ControladorMensagemInterfaceGrafica;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Pasta;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Button;
import java.awt.Label;

public class TelaInicialClient extends JFrame {

	private JPanel contentPane;
	private int contadorId = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicialClient frame = new TelaInicialClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicialClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(188, 90, 1, 1);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(38, 29, 156, 243);
		contentPane.add(list_1);
		
		JButton btnCarregarCoisas = new JButton("Carregar Arquivos");
		btnCarregarCoisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				controlador.carregarArquivos();
				
				//DefaultListModel list = new DefaultListModel();
				
				//list.addElement("corinthians");
				//list.addElement("corinthians2");
				//list_1.setModel(list);
			}
		});
		btnCarregarCoisas.setBounds(227, 49, 163, 25);
		contentPane.add(btnCarregarCoisas);
		
		JButton btnAdd = new JButton("Upar Pasta");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter(".txt","gpx");
				JFileChooser arquivoSeleciona = new JFileChooser(); //Parte JFileChooser
				arquivoSeleciona.setDialogTitle("Abrir Arquivo");
				arquivoSeleciona.setFileSelectionMode(arquivoSeleciona.DIRECTORIES_ONLY);
				arquivoSeleciona.addChoosableFileFilter(filter);
				arquivoSeleciona.showOpenDialog(null);
				File file = arquivoSeleciona.getSelectedFile();
				
				Path path = Paths.get(file.getPath()); // Converte pasta em array de bytes, talvez precise.
			    byte[] data = null;
				try {
					data = Files.readAllBytes(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Pasta pasta = new Pasta(contadorId++,"Nome"); // Adicionando pasta ao Jlist
				DefaultListModel list = new DefaultListModel();
				list.add(contadorId, pasta);
				
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				controlador.uparPasta(pasta);
			}
		});
		btnAdd.setBounds(227, 98, 163, 25);
		contentPane.add(btnAdd);
		
		JButton btnCompartilhar = new JButton("Compartilhar");
		btnCompartilhar.setBounds(27, 284, 175, 25);
		contentPane.add(btnCompartilhar);
		
		JButton btnNewButton = new JButton("Upar Arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser arquivoSeleciona = new JFileChooser(); 
				arquivoSeleciona.setDialogTitle("Abrir Arquivo");
				arquivoSeleciona.setFileSelectionMode(arquivoSeleciona.FILES_ONLY);
				arquivoSeleciona.showOpenDialog(null);
				File file = arquivoSeleciona.getSelectedFile();
				
				Path path = Paths.get(file.getPath()); // Converte arquivo em array de bytes
			    byte[] data = null;
				try {
					data = Files.readAllBytes(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Arquivo arquivo = new Arquivo(file.getName(),data); // Adicionando ao JList
				DefaultListModel list = new DefaultListModel();
				list.add(contadorId, arquivo);
				
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica(); 
				controlador.uparArquivo(arquivo);
			}
		});
		btnNewButton.setBounds(227, 148, 163, 25);
		contentPane.add(btnNewButton);
		
		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem.setBounds(425, 0, 129, 19);
		contentPane.add(menuItem);
		
		JButton btnNewButton_1 = new JButton("Download");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tenho que passar o caminho pra baixar o arquivo//
				
			}
		});
		btnNewButton_1.setBounds(227, 197, 163, 25);
		contentPane.add(btnNewButton_1);
		
	}
}
