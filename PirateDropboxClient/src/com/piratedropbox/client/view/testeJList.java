package com.piratedropbox.client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class testeJList extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testeJList frame = new testeJList();
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
	public testeJList() {
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
				list.addElement("Júlio");
				list.addElement("Eduardo");
				list.addElement("Gustavo");
				list_1.setModel(list);
			}
		});
		btnCarregarCoisas.setBounds(257, 38, 180, 25);
		contentPane.add(btnCarregarCoisas);
		
		JButton btnAdd = new JButton("UparPasta");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(227, 86, 117, 25);
		contentPane.add(btnAdd);
		
		JButton btnCompartilhar = new JButton("Compartilhar");
		btnCompartilhar.setBounds(27, 284, 175, 25);
		contentPane.add(btnCompartilhar);
		
		JButton btnNewButton = new JButton("Upar Arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(356, 86, 163, 25);
		contentPane.add(btnNewButton);
		
		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.setBounds(425, 0, 129, 19);
		contentPane.add(menuItem);
	}
}
