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
import javax.swing.text.IconView;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class TelaInicialClient extends JFrame {
	private Arquivo arquivo;
	private Pasta pasta;
	private int idPastaAtual;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList<Object> jlist;
	private DefaultListModel<Object> listModel;
	private Map<Object, Icon> icons = null;
	private Stack<Integer> pilhaPastas;

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
		listModel.removeAllElements();
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
		Map<Object, Icon> icons = new HashMap<Object, Icon>();
		icons.put(Arquivo.class, new ImageIcon("img/fileIcon.png"));
		icons.put(Pasta.class, MetalIconFactory.getTreeFolderIcon());
		IconListRenderer cellRenderer = new IconListRenderer(icons);
		listModel = new DefaultListModel<>();
		JList<Object> jList = new JList<Object>(listModel);
		System.out.println(listModel.size());
		pilhaPastas = new Stack<>();
		
		setTitle("Tela Ciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 57, 403, 349);
		contentPane.add(scrollPane);

		jlist = new JList<Object>();
		scrollPane.setViewportView(jlist);
		jlist.setCellRenderer(cellRenderer);
		
		jlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && jlist.getSelectedValue() instanceof Pasta){
					System.out.println("pilha no click: "+ pilhaPastas.toString());
					pilhaPastas.push(idPastaAtual);
					idPastaAtual = ((Pasta)jlist.getSelectedValue()).getId();
					
					System.out.println("pilha no click: "+ pilhaPastas.toString());
					ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
					controlador.carregarArquivos(((Pasta)jlist.getSelectedValue()).getId());
				}
				super.mouseClicked(e);
			}
		});

		JButton button = new JButton("Carregar Arquivos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
				controlador.carregarArquivos(idPastaAtual);

				// CustomListCellRendererArquivo clcra = new
				// CustomListCellRendererArquivo();
				// jlist.setCellRenderer(clcra);;

				// DefaultListModel list = new DefaultListModel();

			}

		});
		button.setBounds(439, 54, 163, 25);
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
							}
						}
					}


				// String userName = JOptionPane.showInputDialog("Qual seu nome
				// de usuário?");
				// Arquivo arquivo = new Arquivo(3,"Readme");
				// Mensagem msg = new Mensagem(TAG.SHAREP,14,"gustavo");

			

		});
		btnCompartilhar.setBounds(439, 374, 163, 25);
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
				controlador.uparArquivo(arquivo, idPastaAtual);
			}

		});
		button_2.setBounds(439, 125, 163, 25);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Download");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(jlist.getSelectedValue().getClass());
				if (jlist.getSelectedValue() instanceof Arquivo) {
					arquivo = (Arquivo) jlist.getSelectedValue();
					ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
					controlador.downloadArquivo(arquivo);
				}
			}
			

		});
		button_3.setBounds(439, 207, 163, 25);
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
				controlador.criarPasta(pasta);

			}
		});
		btnCriarPasta.setBounds(439, 289, 163, 25);
		contentPane.add(btnCriarPasta);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pilhaPastas.isEmpty()) {
					System.out.println("pilha ao voltar: "+ pilhaPastas.toString());
					int idPastaAnterior = pilhaPastas.pop();
					System.out.println("pasta anterior: "+idPastaAnterior);
					System.out.println("pilha ao voltar: "+ pilhaPastas.toString());
					ControladorMensagemInterfaceGrafica controlador = new ControladorMensagemInterfaceGrafica();
					controlador.carregarArquivos(idPastaAnterior);
					idPastaAtual = idPastaAnterior;
				}else{
					
				}
			}
		});
		btnVoltar.setBounds(12, 20, 117, 25);
		contentPane.add(btnVoltar);
	}

	public void setPasta(int idPasta) {
		this.idPastaAtual = idPasta;
	}
	
}
