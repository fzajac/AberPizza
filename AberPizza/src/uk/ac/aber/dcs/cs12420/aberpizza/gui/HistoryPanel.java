package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Panel in the HistoryFrame. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class HistoryPanel extends JPanel
{
	private Till till; 
	private JList<Object> ordersList; 
	private JEditorPane selectedOrder;
	private JLabel total; 
	
	public HistoryPanel(Till t)
	{
		till = t; 
		
		// add listener
		HistoryListListener listener = new HistoryListListener(this);
		
		// set layout for this panel
		this.setLayout(new BorderLayout());
		
		// get items from orders ArrayList in Till to String[] array
		String[] orders = new String[till.getOrders().size()];
		for(int i = 0; i < till.getOrders().size(); i++)
			orders[i] = till.getOrders().get(i).getDate().toString() + " - \u00A3" + till.getOrders().get(i).getSubtotal();
		
		// List of orders: 
		ordersList = new JList<Object>(orders);
		ordersList.addListSelectionListener(listener);
		ordersList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		JScrollPane ordersScroll = new JScrollPane(ordersList);
		
		// JEditorPane displaying selected order: 
		selectedOrder = new JEditorPane("text/html", "");
		selectedOrder.setEditable(false);
		JScrollPane selectedScroll = new JScrollPane(selectedOrder);
		selectedScroll.setPreferredSize(new Dimension(300, selectedScroll.getHeight()));
		
		// JLabel displaying total: 
		total = new JLabel("Total: \u00A3" + till.getTotalForDay().toString() + " | Number of orders: " + till.getOrders().size());
		total.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
		// Add all the components to the panel: 
		this.add(ordersScroll, BorderLayout.CENTER);
		this.add(selectedScroll, BorderLayout.EAST);
		this.add(total, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates content in the selectedOrder to currently selected item on the list of orders. 
	 */
	public void updateSelected()
	{
		if(ordersList.getSelectedIndex() != -1)
			selectedOrder.setText(till.getOrders().get(ordersList.getSelectedIndex()).getReceipt());
	}
	
	/**
	 * Updates the list of orders. 
	 */
	public void updateList()
	{
		// get items from orders ArrayList in Till to String[] array
		String[] orders = new String[till.getOrders().size()];
		for(int i = 0; i < till.getOrders().size(); i++)
			orders[i] = till.getOrders().get(i).getDate().toString() + " - \u00A3" + till.getOrders().get(i).getSubtotal();
		
		// set new data in orders list
		ordersList.setListData(orders);
		
		// set new text in total label
		total.setText("Total: \u00A3" + till.getTotalForDay().toString() + " | Number of orders: " + till.getOrders().size());
		
		this.repaint();
		this.revalidate();
	}
}
