package com.piratedropbox.server;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.plaf.metal.MetalIconFactory;

public class IconListRenderer extends DefaultListCellRenderer {

private Map<Object, Icon> icons = null;
	
	public IconListRenderer(Map<Object, Icon> icons) {
		this.icons = icons;
	}
	
	@Override
	public Component getListCellRendererComponent(
		JList list, Object value, int index, 
		boolean isSelected, boolean cellHasFocus) {
		
		// Get the renderer component from parent class
		
		JLabel label = 
			(JLabel) super.getListCellRendererComponent(list, 
				value, index, isSelected, cellHasFocus);
		
		// Get icon to use for the list item value
		
		Icon icon = icons.get(value);
		
		// Set icon to display for value
		
		label.setIcon(icon);
		return label;
	}

	public static void main(String[] args) {

		// setup mappings for which icon to use for each value
		
		Map<Object, Icon> icons = new HashMap<Object, Icon>();
		icons.put("details", 
			MetalIconFactory.getFileChooserDetailViewIcon());
		icons.put("folder", 
			MetalIconFactory.getTreeFolderIcon());
		icons.put("computer", 
			MetalIconFactory.getFileChooserListViewIcon() );
		
		JFrame frame = new JFrame("Icon List");
		frame.setDefaultCloseOperation(
			JFrame.DISPOSE_ON_CLOSE);
		
		// create a list with some test data
		
		new JList<Object>();
		
		JList list = new JList(new Object[] {"details", "computer", "folder", "computer"});
		
		// create a cell renderer to add the appropriate icon
		
		list.setCellRenderer(new IconListRenderer(icons));
		frame.add(list);
		frame.pack();
		frame.setVisible(true);
	}
	
}
