package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Panel holding current order. 
 * Displayed in MainFrame, on the right. 
 * @author FZajac
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel
{
	private OrderNamePanel customerName;
	private OrderBottomPanel bottom;
	
	private Object[][] data;
	private Order order;
	private Till till;
	
	private JScrollPane scrollPane;
	private String[] columnNames = {"Name", "Price", "Q-ty"};
	
	private JTable orderTable; 
	
	public OrderPanel(Order o, Till t)
	{
		order = o; 
		till = t; 
		
		// set panel's layout 
		this.setLayout(new BorderLayout());
		
		// set panel's size
		this.setSize(300,this.getHeight());
		
		// draw elements on the panel
		drawElements();
	}
	
	/**
	 * Updates elements in order panel. 
	 */
	public void updateTable()
	{
		// set customer's name in Order
		order.setCustomerName(customerName.getCustomerName());
		
		// get selected row in the table 
		int selected = orderTable.getSelectedRow();
		
		// redraw all elements in this panel
		this.removeAll();
		drawElements();
		
		// set previously selected row in the table
		if(selected > orderTable.getRowCount()-1)
			selected--;
		if(selected > -1)
			orderTable.setRowSelectionInterval(selected, selected);
	}
	
	/**
	 * Resets everything in order panel by removing all the elements and drawing them again. 
	 * @see #drawElements()
	 */
	public void resetTable()
	{
		this.removeAll();
		drawElements();
	}
	
	/**
	 * Draws all the elements in order panel. 
	 */
	private void drawElements()
	{
		customerName = new OrderNamePanel(order);
		
		data = order.getItemsArray();
		
		orderTable = new JTable(data, columnNames)
		{
			public boolean isCellEditable(int row,int column){ 
				return false; 
			}
		};
		orderTable.setRowHeight(32);
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderTable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		orderTable.setEnabled(true);
		orderTable.setFillsViewportHeight(true);
		orderTable.setShowGrid(false);
		orderTable.getTableHeader().setReorderingAllowed(false);
		orderTable.getColumnModel().getColumn(1).setMaxWidth(40);
		orderTable.getColumnModel().getColumn(2).setMaxWidth(40);
		orderTable.getColumnModel().getColumn(0).setResizable(false);
		orderTable.getColumnModel().getColumn(1).setResizable(false);
		orderTable.getColumnModel().getColumn(2).setResizable(false);
		
		scrollPane = new JScrollPane(orderTable);
		scrollPane.setSize(this.getMinimumSize());
		
		bottom = new OrderBottomPanel(order, this);
		
		this.add(customerName, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
	}
	
	/**
	 * Gets selected row of the order's table. 
	 * @return selected row of the order's table. 
	 */
	public int getTablePosition()
	{
		return orderTable.getSelectedRow();
	}
	
	/**
	 * Adds current order to till. 
	 */
	public void addToTill()
	{
		till.addOrder(order);
	}
}
