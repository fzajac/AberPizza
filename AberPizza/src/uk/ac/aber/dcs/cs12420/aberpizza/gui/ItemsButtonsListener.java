package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;

/**
 * Listener for the ItemsButtons class. 
 * @author FZajac
 * @see ItemsButtons
 */
public class ItemsButtonsListener implements ActionListener
{
	private ArrayList<GeneralItem> itemsList;
	private Items items; 
	private ItemsTab tab;
	
	public ItemsButtonsListener(ArrayList<GeneralItem> iL, Items i, ItemsTab t)
	{
		itemsList = iL;
		items = i;
		tab = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String event = e.getActionCommand();
		if(event.equals("Add item"))
		{
			// get description of the item
			String description;
			do
			{
				description = JOptionPane.showInputDialog(null, "Description of the item: ", "Add an item", JOptionPane.QUESTION_MESSAGE);
				if(description == null)
					break; 
			}
			while(description.equals(""));
			
			if(description != null)
			{
				// set price of the item
				BigDecimal price = new BigDecimal("0.00");
				boolean valid = true; 
				do
				{
					valid = true;
					try
					{
						price = new BigDecimal(JOptionPane.showInputDialog(null, "Price of the item: ", "Add an item", JOptionPane.QUESTION_MESSAGE));
					}
					catch(NullPointerException npe)
					{
						break;
					}
					catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(null, "Incorrect value!", "Error", JOptionPane.ERROR_MESSAGE);
						valid = false;
					}
				}
				while(valid == false);
				if(price.compareTo(new BigDecimal("0.00")) > 0) // has to be greater than 0.00
				{
					itemsList.add(new GeneralItem(price, description)); // add an item
					items.save(); // save 
					tab.updateList(); // update list of items
				}
			}
		}
		else if(event.equals("Remove item"))
		{
			itemsList.remove(tab.getListPosition()); // remove an item
			items.save(); // save
			tab.updateList(); // update list of items
		}
	}
}
