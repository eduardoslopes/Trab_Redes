package com.piratedropbox.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.DefaultListCellRenderer;




public class CustomListCellRendererPasta extends DefaultListCellRenderer {
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		//System.out.println(imagem.getIconHeight());

		// Instancia um JLabel e define a imagem a ser visualizada
		JLabel labelPasta = new JLabel(new ImageIcon("/home/julioserafim/Documentos/Java/PirateDropboxClient/img/folder.png"));
//		JLabel labelArquivo = new JLabel(new ImageIcon("/home/julioserafim/Documentos/Java/PirateDropboxClient/img/file.png"));
		// Define o texto do JLabel
		
		labelPasta.setText(value.toString());

		// Quando o item é seleccionado, é ao mesmo tempo alinhado ao centro.
		//if (isSelected)
		//	label.setHorizontalAlignment(SwingConstants.CENTER);
		//else
			labelPasta.setHorizontalAlignment(SwingConstants.LEFT);

		// Retorna o Label
		return labelPasta;
	}
	
	

}
