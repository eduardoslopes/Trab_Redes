package com.piratedropbox.view;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;




public class CustomListCellRendererArquivo extends JLabel implements ListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		//System.out.println(imagem.getIconHeight());

		// Instancia um JLabel e define a imagem a ser visualizada
		JLabel labelArquivo = new JLabel(new ImageIcon("/home/julioserafim/Documentos/Java/PirateDropboxClient/img/file.png"));
		// Define o texto do JLabel
		
		labelArquivo.setText(value.toString());

		// Quando o item é seleccionado, é ao mesmo tempo alinhado ao centro.
		//if (isSelected)
		//	label.setHorizontalAlignment(SwingConstants.CENTER);
		//else
			labelArquivo.setHorizontalAlignment(SwingConstants.LEFT);

		// Retorna o Label
		return labelArquivo;
	}

	
}

