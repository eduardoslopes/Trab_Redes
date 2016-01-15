package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.piratedropbox.controller.ControladorMensagemInterfaceGrafica;
import com.piratedropbox.controller.ControladorRecebecimento;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.TAG;

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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.Label;

public class TelaInicialClient extends JFrame {

	private JPanel contentPane;
	private int contadorId = 0;
	private Pasta pasta;
	private Arquivo arquivo;
	private JList list_1;
	DefaultListModel<Object> list;
	private Login telaLogin;

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

	public void carregarObjetos(List<Object> arquivos) {
		if (arquivos != null) {
			for (Object arquivo : arquivos) {
				if (arquivo instanceof Arquivo)
					System.out.println(((Arquivo) arquivo).getNome());
					list.addElement((Arquivo) arquivo);
				if (arquivo instanceof Pasta)
					list.addElement((Pasta) arquivo);
			}
			System.out.println("OLAOLAOLA " + list == null);
			list_1.setModel(list);
		}
		

	}

	/**
	 * Create the frame.
	 */
	public TelaInicialClient() {// Login telaLogin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList list_1 = new JList();
		list_1.setBounds(38, 29, 156, 243);
		list = new DefaultListModel<Object>();
		contentPane.add(list_1);

		// public void método pra carregar objetos

		JButton btnCarregarCoisas = new JButton("Carregar Arquivos");
		btnCarregarCoisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ControladorMensagemInterfaceGrafica controlador = new
				// ControladorMensagemInterfaceGrafica();
				// controlador.carregarArquivos();

				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.carregarArquivos();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				CustomListCellRendererArquivo clcra = new CustomListCellRendererArquivo();
				list_1.setCellRenderer(clcra);

				DefaultListModel list = new DefaultListModel();

				// list.addElement(pasta);
				// list_1.setModel(list);

				// list.addElement("corinthians2");
				// list_1.setModel(list);
			}
		});
		btnCarregarCoisas.setBounds(227, 49, 163, 25);
		contentPane.add(btnCarregarCoisas);

		JButton btnCompartilhar = new JButton("Compartilhar"); // Id arquivo, id
																// pasta. Tela:
																// Quem
																// compartilhar:
																// Username.
		btnCompartilhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = JOptionPane.showInputDialog("Qual seu nome de usuário?");
				// Arquivo arquivo = new Arquivo(3,"Readme");
				// Mensagem msg = new Mensagem(TAG.SHAREP,14,"gustavo");

				// id 14 user "gustavo"

				// ControladorMensagemInterfaceGrafica controladorArquivo = new
				// ControladorMensagemInterfaceGrafica();// Compartilha Arquivo
				// try {
				// controladorArquivo.compartilharArquivo(arquivo,userName);
				// } catch (NumberFormatException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

				ControladorMensagemInterfaceGrafica controladorPasta = new ControladorMensagemInterfaceGrafica();// Compartilha
																													// Arquivo
				try {
					controladorPasta.compartilharPasta(14, "gustavo");
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
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

				Path path = Paths.get(file.getPath()); // Converte arquivo em
														// array de bytes
				byte[] data = null;
				try {
					data = Files.readAllBytes(path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Arquivo arquivo = new Arquivo(file.getName(), data);// Adicionando
																	// ao JList

				list.addElement(arquivo);
				list_1.setModel(list);
				CustomListCellRendererArquivo campoArquivo = new CustomListCellRendererArquivo();// Renderizar
																									// só
																									// pasta
																									// com
																									// imagem
																									// de
																									// pasta
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getClass().isInstance(arquivo)) {
						list_1.setCellRenderer(campoArquivo);
					}
				}
				System.out.println(list.size());

				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.uparArquivo(arquivo, 4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(227, 116, 163, 25);
		contentPane.add(btnNewButton);

		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Login login = new Login();
				// login.setVisible(true);
				System.exit(0);
			}
		});
		menuItem.setBounds(425, 0, 129, 19);
		contentPane.add(menuItem);

		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arquivo arquivo = new Arquivo(4, "ISAC");
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.downloadArquivo(arquivo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// try {
				// System.out.println("entrou no try");
				// ControladorRecebecimento controladorRecebimento = new
				// ControladorRecebecimento();
				// controladorRecebimento.recebeConexao();
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

			}
		});
		btnDownload.setBounds(227, 182, 163, 25);
		contentPane.add(btnDownload);
	}

}