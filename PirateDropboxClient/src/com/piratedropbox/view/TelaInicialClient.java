package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.piratedropbox.model.Pasta;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import java.awt.Button;
import java.awt.Label;

public class TelaInicialClient extends JFrame {

	private JPanel contentPane;

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
				DefaultListModel list = new DefaultListModel();
				
				list.addElement("Eduardo");
				list.addElement("Gustavo");
				list_1.setModel(list);
			}
		});
		btnCarregarCoisas.setBounds(227, 49, 163, 25);
		contentPane.add(btnCarregarCoisas);
		
		JButton btnAdd = new JButton("UparPasta");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser arquivoSeleciona = new JFileChooser();
				arquivoSeleciona.setDialogTitle("Abrir Arquivo");
				int resultado = arquivoSeleciona.showOpenDialog(null);
				int contadorId = 0;
				 // Transformar em Array de Bytes
				
				Pasta pasta = new Pasta(contadorId++,"Nome");
				DefaultListModel list = new DefaultListModel();
				list.add(contadorId, pasta);
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
			}
		});
		btnNewButton.setBounds(227, 148, 163, 25);
		contentPane.add(btnNewButton);
		
		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.setBounds(425, 0, 129, 19);
		contentPane.add(menuItem);
		
		JButton btnNewButton_1 = new JButton("Download");
		btnNewButton_1.setBounds(227, 197, 163, 25);
		contentPane.add(btnNewButton_1);
		
	}
}