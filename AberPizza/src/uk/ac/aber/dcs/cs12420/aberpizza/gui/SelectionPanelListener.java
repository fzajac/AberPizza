package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Type;

/**
 * Listener for the SelectionPanel. 
 * @author Filip Zajac (fiz)
 * @see SelectionPanel
 */
public class SelectionPanelListener implements ActionListener
{
	private SelectionPanel selectionPanel;
	private Order order;
	private OrderPanel orderPanel;
	
	private int mode = 0; 
	
	public SelectionPanelListener(SelectionPanel sP, Order o, OrderPanel oP)
	{
		selectionPanel = sP;
		order = o; 
		orderPanel = oP;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String event = e.getActionCommand();
		if (event.equals("Back"))
		{
			mode = 0; 
			selectionPanel.setMode(0);
		}
		else if (event.equals("Pizzas"))
		{
			mode = 1; 
			selectionPanel.setMode(1);
		}
		else if (event.equals("Sides"))
		{
			mode = 2; 
			selectionPanel.setMode(2);
		}
		else if (event.equals("Drinks"))
		{
			mode = 3; 
			selectionPanel.setMode(3);
		}
		else
		{
			// separate description and price of an item from the name of the button
			String description = ""; 
			int i; 
			String price = ""; 
			for(i = 0; i < event.length(); i++)
			{
				if(event.charAt(i) == ' ' && event.charAt(i+1) == '-')
					break;
				else
					description += event.charAt(i);
			}
			for(i = i+3; i < event.length(); i++)
			{
				price += event.charAt(i);
			} 
			
			// depending on mode, add to order new item of specified type. 
			if(mode == 1)
				order.addItem(new GeneralItem(new BigDecimal(price), description, Type.PIZZA), 1);
			else if(mode == 2)
				order.addItem(new GeneralItem(new BigDecimal(price), description, Type.SIDE), 1);
			else if(mode == 3)
				order.addItem(new GeneralItem(new BigDecimal(price), description, Type.DRINK), 1);
			orderPanel.updateTable();
			selectionPanel.setMode(0);
		}
	}

}
