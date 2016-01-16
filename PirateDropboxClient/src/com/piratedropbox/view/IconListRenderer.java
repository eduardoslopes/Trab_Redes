package com.piratedropbox.view;

import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.plaf.metal.MetalIconFactory;

import com.piratedropbox.model.Arquivo;
import com.piratedropbox.model.Pasta;

public class IconListRenderer extends DefaultListCellRenderer {

private Map<Object, Icon> icons = null;
	
	public IconListRenderer(Map<Object, Icon> icons) {
		this.icons = icons;
	}
	
	@Override
	public Component getListCellRendererComponent(
		JList list, Object value, int index, 
		boolean isSelected, boolean cellHasFocus) {

		JLabel label = (JLabel) super.getListCellRendererComponent(list, 
				value, index, isSelected, cellHasFocus);
		
		Icon icon = icons.get(value.getClass());
		label.setIcon(icon);
		return label;
	}

	public static void main(String[] args) {
		
		Map<Object, Icon> icons = new HashMap<Object, Icon>();
		icons.put(Arquivo.class, 
			MetalIconFactory.getFileChooserHomeFolderIcon());
		icons.put(Pasta.class, 
			MetalIconFactory.getTreeFolderIcon());
		
		
		JFrame frame = new JFrame("Icon List");
		frame.setDefaultCloseOperation(
			JFrame.DISPOSE_ON_CLOSE);
		
		// create a list with some test data
		
		//new JList<Object>();
		
		Arquivo a1 = new Arquivo(1, "edu");
		Arquivo a2 = new Arquivo(2, "dudu");
		Pasta p1 = new Pasta(3, "mii");
		Pasta p2 = new Pasta(4, "milena");
		
		
		Object[] objetos = {a1, p1, a2, p2};
		
		JList list = new JList(objetos);
		// create a cell renderer to add the appropriate icon
		IconListRenderer iconListRenderer = new IconListRenderer(icons);
		
		list.setCellRenderer(iconListRenderer);
		frame.add(list);
		frame.pack();
		frame.setVisible(true);
	}
	
}
