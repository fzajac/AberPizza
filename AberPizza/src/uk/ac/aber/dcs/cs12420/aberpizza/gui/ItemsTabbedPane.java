package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JTabbedPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;

/**
 * Tabs with pizzas, sides, and drinks for ItemsFrame. 
 * @author FZajac
 */
@SuppressWarnings("serial")
public class ItemsTabbedPane extends JTabbedPane
{
	public ItemsTabbedPane(Items items)
	{
		// create and add 3 tabs
		
		ItemsTab pizzas = new ItemsTab(items.getPizzas(), items);
		ItemsTab sides = new ItemsTab(items.getSides(), items);
		ItemsTab drinks = new ItemsTab(items.getDrinks(), items);
		
		this.addTab("Pizzas", pizzas);
		this.addTab("Sides", sides);
		this.addTab("Drinks", drinks);
	}
}
