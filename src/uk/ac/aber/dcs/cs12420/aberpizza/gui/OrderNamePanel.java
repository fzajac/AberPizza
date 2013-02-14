package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * Panel for editing customer's name. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class OrderNamePanel extends JPanel
{
	private JTextField customerName;
	public OrderNamePanel(Order order)
	{
		// set panel's layout
		this.setLayout(new BorderLayout());
		
		JLabel enterName = new JLabel("Customer: ");
		customerName = new JTextField(order.getCustomerName()); // gets name from Order
		
		// add components to the panel
		this.add(enterName, BorderLayout.WEST);
		this.add(customerName, BorderLayout.CENTER);
	}
	
	/**
	 * gets customer's name from the text field
	 * @return customer's name
	 */
	public String getCustomerName()
	{
		return customerName.getText();
	}
}
