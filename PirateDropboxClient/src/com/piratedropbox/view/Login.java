
package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.piratedropbox.controller.ControladorMensagemInterfaceGrafica;
import com.piratedropbox.controller.ControladorRecebecimento;
import com.piratedropbox.controller.Criptografia;
import com.piratedropbox.controller.InterpreterMessage;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Usuario;
import com.piratedropbox.test.Main;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	public boolean podeLogar;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(79, 57, 70, 15);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(79, 84, 70, 15);
		contentPane.add(lblSenha);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtLogin.getText();
				String senha = null;
				try {
					senha = Criptografia.convertPasswordToMD5(txtSenha.getText());					
				} catch (NoSuchAlgorithmException e2) {
					//TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Usuario usuario = new Usuario(userName,senha);
				System.out.println(senha);
				
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				txtLogin.setText("");
				txtSenha.setText("");
				System.out.println(senha);
				controlador.loginUsuario(usuario);
				
				
				
			}
		});
		btnAcessar.setBounds(64, 111, 117, 25);
		contentPane.add(btnAcessar);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(133, 55, 117, 19);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(133, 84, 117, 19);
		contentPane.add(txtSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.criarUsuario.setVisible(true);
				Main.login.setVisible(false);
	
			}
		});
		btnCadastrar.setBounds(193, 111, 117, 25);
		contentPane.add(btnCadastrar);
	}

	public void setPodeLogar(boolean podeLogar) {
		this.podeLogar = podeLogar;
	}
}
