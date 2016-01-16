package com.piratedropbox.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalIconFactory;

import com.piratedropbox.controller.ControladorMensagemInterfaceGrafica;
import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Mensagem;
import com.piratedropbox.model.Pasta;
import com.piratedropbox.model.TAG;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class TelaInicialClient extends JFrame {
	private Arquivo arquivo;
	private Pasta pasta;
	private int idPastaAtual;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList<Object> jlist;
	private DefaultListModel<Object> listModel = new DefaultListModel<Object>();
	Map<Object, Icon> icons = null;

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

	// public void retornarListaModel(DefaultListModel<Object> listModel){
	// for(int i = 0; i < listModel.size(); i++){

	// }
	// }

	public void carregarObjetos(List<Object> arquivos) {

		if (arquivos != null) {
			for (Object arquivo : arquivos) {
				if (arquivo instanceof Arquivo) {
					listModel.addElement((Arquivo) arquivo);

				}

				if (arquivo instanceof Pasta) {
					listModel.addElement((Pasta) arquivo);
				}

				// jlist.setCellRenderer(new IconListRenderer(icons));

			}
			// System.out.println("OLAOLAOLA " + list == null);
			// list_1.setModel(list);
			System.out.println(listModel.size());
			System.out.println("OFICIAL");
			jlist.setModel(listModel); // Adicionar os elementos
			
		}

	}

	/**
	 * Create the frame.
	 */
	public TelaInicialClient() {
		JList<Object> jList = new JList<Object>(listModel);
		System.out.println(listModel.size());
		Map<Object, Icon> icons = new HashMap<Object, Icon>(); // Criando MAP
		icons.put(arquivo, new ImageIcon("/home/julioserafim/Documentos/Java/PirateDropboxClient/img/file.png"));
		icons.put(pasta, new ImageIcon("/home/julioserafim/Documentos/Java/PirateDropboxClient/img/folder.png"));

		setTitle("Tela Ciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 12, 303, 371);
		contentPane.add(scrollPane);

		jlist = new JList<Object>();
		scrollPane.setViewportView(jlist);

		JButton button = new JButton("Carregar Arquivos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.carregarArquivos(idPastaAtual);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// CustomListCellRendererArquivo clcra = new
				// CustomListCellRendererArquivo();
				// jlist.setCellRenderer(clcra);;

				// DefaultListModel list = new DefaultListModel();

			}

		});
		button.setBounds(380, 46, 163, 25);
		contentPane.add(button);

		JButton btnCompartilhar = new JButton("Compartilhar");

		btnCompartilhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jlist.getSelectedValue() instanceof Arquivo) {
					arquivo = (Arquivo) jlist.getSelectedValue();
					String userName = JOptionPane.showInputDialog("Qual seu nome de usuário?");

					ControladorMensagemInterfaceGrafica controladorArquivo = new ControladorMensagemInterfaceGrafica();// Compartilha
																														// Arquivo
					try {
						controladorArquivo.compartilharArquivo(arquivo, userName);
					} catch (NumberFormatException e1) {

						e1.printStackTrace();
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				}

						if (jlist.getSelectedValue() instanceof Pasta) {
							pasta = (Pasta) jlist.getSelectedValue();
							String userName = JOptionPane.showInputDialog("Qual seu nome de usuário?");

							ControladorMensagemInterfaceGrafica controladorPasta = new ControladorMensagemInterfaceGrafica(); // Compartilha
																																// Pasta
							try {
								controladorPasta.compartilharPasta(pasta.getId(), userName);
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}


				// String userName = JOptionPane.showInputDialog("Qual seu nome
				// de usuário?");
				// Arquivo arquivo = new Arquivo(3,"Readme");
				// Mensagem msg = new Mensagem(TAG.SHAREP,14,"gustavo");

			

		});
		btnCompartilhar.setBounds(33, 395, 175, 25);
		contentPane.add(btnCompartilhar);

		JButton button_2 = new JButton("Upar Arquivo");
		button_2.addActionListener(new ActionListener() {
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

				listModel.addElement(arquivo);
				jlist.setModel(listModel);

				// CustomListCellRendererArquivo campoArquivo = new
				// CustomListCellRendererArquivo();// Renderizar

				IconListRenderer iconRenderizado = new IconListRenderer(icons);
				jlist.setCellRenderer(iconRenderizado);
				System.out.println("impressao" + icons);// só
														// pasta
														// com
														// imagem
														// de
														// pasta
				// for (int i = 0; i < listModel.size(); i++) {
				// if (listModel.get(i).getClass().isInstance(arquivo)) {
				// jlist.setCellRenderer(campoArquivo);
				// }
				// }
				// System.out.println(listModel.size());
				System.out.println("id pasta atual fela:" + idPastaAtual);
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.uparArquivo(arquivo, idPastaAtual);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		button_2.setBounds(380, 91, 163, 25);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Download");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(jlist.getSelectedValue().getClass());
				if (jlist.getSelectedValue() instanceof Arquivo) {
					arquivo = (Arquivo) jlist.getSelectedValue();
					ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
					try {
						controlador.downloadArquivo(arquivo);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			

		});
		button_3.setBounds(380, 140, 163, 25);
		contentPane.add(button_3);

		JMenuItem menuItem = new JMenuItem("Sair");
		menuItem.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuItem.setBounds(428, 0, 129, 19);
		contentPane.add(menuItem);

		JButton btnCriarPasta = new JButton("Criar Pasta");
		btnCriarPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomePasta = JOptionPane.showInputDialog("Qual o nome da pasta?");
				Pasta pasta = new Pasta(nomePasta, idPastaAtual);
				listModel.addElement(pasta);

				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				try {
					controlador.criarPasta(pasta);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnCriarPasta.setBounds(380, 187, 163, 25);
		contentPane.add(btnCriarPasta);
	}

	public void setPasta(int idPasta) {
		this.idPastaAtual = idPasta;
	}
	
	
}
