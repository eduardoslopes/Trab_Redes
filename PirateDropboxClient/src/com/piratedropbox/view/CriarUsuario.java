package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.piratedropbox.controller.ControladorMensagemInterfaceGrafica;
import com.piratedropbox.controller.ControladorRecebecimento;
import com.piratedropbox.controller.Criptografia;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CriarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField txtNome;
	private JTextField textSobrenome;
	private JPasswordField textSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarUsuario frame = new CriarUsuario();
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
	public CriarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(43, 29, 103, 15);
		contentPane.add(lblUsername);

		textUsername = new JTextField();
		textUsername.setBounds(164, 27, 114, 19);
		contentPane.add(textUsername);
		textUsername.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(43, 56, 70, 15);
		contentPane.add(lblNome);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(43, 99, 103, 15);
		contentPane.add(lblSobrenome);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(43, 130, 70, 15);
		contentPane.add(lblSenha);

		txtNome = new JTextField();
		txtNome.setBounds(164, 58, 114, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		textSobrenome = new JTextField();
		textSobrenome.setBounds(164, 97, 114, 19);
		contentPane.add(textSobrenome);
		textSobrenome.setColumns(10);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtNome.getText();
				String nome = textSobrenome.getText();
				String sobrenome = textSobrenome.getText();

				String senha = textSenha.getText();
				try {
					senha = Criptografia.convertPasswordToMD5(textSenha.getText()); // Criptografar
																						// senha
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Usuario usuario = new Usuario(username, senha, nome, sobrenome);
				txtNome.setText("");
				textSobrenome.setText("");
				textSenha.setText("");
				textUsername.setText("");
				System.out.println(senha);
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				controlador.criarUsuario(usuario);

			}
		});
		btnCadastrar.setBounds(164, 193, 117, 25);
		contentPane.add(btnCadastrar);

		textSenha = new JPasswordField();
		textSenha.setBounds(164, 128, 114, 19);
		contentPane.add(textSenha);
	}
}
