package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * Bottom part of the OrderPanel. 
 * Holds buttons for increasing and decreasing quantity of items in the order, discount and subtotal labels, pay and cancel buttons. 
 * @author Filip Zajac
 */
@SuppressWarnings("serial")
public class OrderBottomPanel extends JPanel
{
	private OrderBottomPanelListener listener;
	public OrderBottomPanel(Order order, OrderPanel orderPanel)
	{
		listener = new OrderBottomPanelListener(order, orderPanel);
		this.setLayout(new GridLayout(4, 2));
		
		// buttons for increasing and decreasing quantity of items in the order
		JButton add = new JButton("+");
		add.addActionListener(listener);
		JButton subtract = new JButton("-");
		subtract.addActionListener(listener);
		
		// discounts labels
		JLabel discounts = new JLabel("Discount: ", JLabel.RIGHT);
		JLabel discountsSum = new JLabel("\u00A3" + order.getDiscount().toString(), JLabel.LEFT);
		
		// subtotal labels
		JLabel subtotal = new JLabel("Subtotal: ", JLabel.RIGHT);
		JLabel subtotalSum = new JLabel("\u00A3" + order.getSubtotal().toString(), JLabel.LEFT);
		
		// buttons for paying and canceling the order
		JButton pay = new JButton("Pay");
		pay.addActionListener(listener);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(listener);
		
		// add elements to the panel
		this.add(add);
		this.add(subtract);
		this.add(discounts);
		this.add(discountsSum);
		this.add(subtotal);
		this.add(subtotalSum);
		this.add(pay);
		this.add(cancel);
	}
}
