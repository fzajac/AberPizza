package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;

/**
 * Buttons for adding and removing items. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class ItemsButtons extends JPanel
{
	private ItemsButtonsListener listener;
	public ItemsButtons(ArrayList<GeneralItem> itemsList, Items items, ItemsTab tab)
	{
		// listener
		listener = new ItemsButtonsListener(itemsList, items, tab);
		
		// layout
		this.setLayout(new GridLayout(1,2));
		
		// buttons
		JButton add = new JButton("Add item"); 
		JButton remove = new JButton("Remove item");
		
		// add listeners 
		add.addActionListener(listener);
		remove.addActionListener(listener);
		
		// add components
		this.add(add);
		this.add(remove);
	}
}
