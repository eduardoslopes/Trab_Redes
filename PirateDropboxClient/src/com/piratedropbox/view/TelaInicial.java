package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlImgPasta = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/folder.png")).getImage();
		jlImgPasta.setIcon(new ImageIcon(img));
		jlImgPasta.setBounds(27, 0, 255, 255);
		contentPane.add(jlImgPasta);
		
		JButton btnNewButton = new JButton("Compartilhar");
		btnNewButton.setBounds(70, 280, 157, 25);
		contentPane.add(btnNewButton);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(300, 48, 117, 25);
		contentPane.add(btnAdicionar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setBounds(319, 0, 129, 19);
		contentPane.add(mntmSair);
		
		JButton btnVerMeusArquivos = new JButton("Meus Arquivos");
		btnVerMeusArquivos.setBounds(70, 311, 157, 25);
		contentPane.add(btnVerMeusArquivos);
		Image imgAdd = new ImageIcon(this.getClass().getResource("/iconAdd.png")).getImage();
		
		
	}
}
