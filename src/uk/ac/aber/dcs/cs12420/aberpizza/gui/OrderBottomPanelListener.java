package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * Listener for OrderBottomPanel class. 
 * @author Filip Zajac (fiz)
 * @see OrderBottomPanel
 */
public class OrderBottomPanelListener implements ActionListener
{
	private OrderPanel orderPanel;
	private Order order;
	
	/**
	 * @param o Order
	 * @param oP OrderPanel
	 */
	public OrderBottomPanelListener(Order o, OrderPanel oP)
	{
		order = o;
		orderPanel = oP;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if(event == "Cancel")
		{
			// clear the order and the table in OrderPanel. 
			order.clear();
			orderPanel.resetTable();
			orderPanel.repaint();
			orderPanel.revalidate();
		}
		else if(event.equals("Pay"))
		{
			if(order.getItems().size() > 0)
			{
				// pay for the order. 
				
				// get tender: 
				BigDecimal tender = new BigDecimal("0.00"); 
				boolean valid = true; 
				do
				{
					valid = true;
					try
					{
						tender = new BigDecimal(JOptionPane.showInputDialog("Tender: ", order.getSubtotal()));
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
				while (tender.compareTo(order.getSubtotal()) == -1 || valid == false);
				
				// give change: 
				if(tender.compareTo(new BigDecimal("0.00")) != 0)
				{
					order.setTender(tender);
					order.setChange(order.getTender().subtract(order.getSubtotal()));
					
					if(tender.compareTo(order.getSubtotal()) != 0)
						JOptionPane.showMessageDialog(null, "Please give " + order.getChange() + " change to the customer. ", "Change", JOptionPane.INFORMATION_MESSAGE);
				
					orderPanel.updateTable();
					
					// if customer's name has not been set, set it to (anonymous)
					if(order.getCustomerName().equals(""))
						order.setCustomerName("(anonymous)");
				
					// set date for the order
					Date date = new Date(); 
					order.setDate(date);
					
					// show receipt: 
					JEditorPane pane = new JEditorPane("text/html", order.getReceipt());
					pane.setEditable(false);
					JOptionPane.showMessageDialog(null, pane, "Recipt", JOptionPane.PLAIN_MESSAGE, null);
					
					// add paid order to till
					orderPanel.addToTill();
					
					// clear current order
					order.clear();
					order.setCustomerName("");
					
					orderPanel.resetTable();
					orderPanel.repaint();
					orderPanel.revalidate();
				}
			}
		}
		else if(event == "+")
		{
			// increase quantity of an item
			if(orderPanel.getTablePosition() > -1)
			{
				order.getItem(orderPanel.getTablePosition()).setQuantity(order.getItem(orderPanel.getTablePosition()).getQuantity()+1);
				orderPanel.updateTable();
				orderPanel.repaint();
				orderPanel.revalidate();
			}
		}
		else if(event == "-")
		{
			// decrease quantity of an item
			if(orderPanel.getTablePosition() > -1)
			{
				order.getItem(orderPanel.getTablePosition()).setQuantity(order.getItem(orderPanel.getTablePosition()).getQuantity()-1);
				
				// if quantity of an item equals 0, it will be removed. 
				if(order.getItem(orderPanel.getTablePosition()).getQuantity() == 0)
					order.removeItem(orderPanel.getTablePosition());
				
				orderPanel.updateTable();
				orderPanel.repaint();
				orderPanel.revalidate();
			}
		}
	}
}
