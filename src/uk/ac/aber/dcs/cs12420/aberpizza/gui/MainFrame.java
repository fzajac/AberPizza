package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Main window of the application. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private Items items = new Items();
	private Till till;
	private Order order = new Order();
	
	private HistoryFrame historyFrame;
	private ItemsFrame itemsFrame;
	
	private MainMenu menu;
	private OrderPanel orderPanel;
	private SelectionPanel selectionPanel;
	
	/**
	 * Listener for this window. 
	 */
	private MainFrameWindowListener listener;
	
	public MainFrame()
	{
		// load the till
		try 
		{
			till = Till.load();
		} catch (IOException e) 
		{
			till = new Till();
		}
		
		// create the listener for this window
		listener = new MainFrameWindowListener(till, this);

		// create panels for this window
		orderPanel = new OrderPanel(order, till);
		selectionPanel = new SelectionPanel(this, items, order, orderPanel);
		
		// create history and items windows
		historyFrame = new HistoryFrame(till);
		itemsFrame = new ItemsFrame(items);
		
		// create menu for  this window
		menu = new MainMenu(historyFrame, itemsFrame, till);
		
		this.setJMenuBar(menu); // set menu 
		this.setSize(700, 450); // set frame size
		this.setLocationRelativeTo(null); // position the frame (center)
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // do nothing on close (use listener for this)
		this.setTitle("AberPizza"); // frame's title
		this.setVisible(true); // visible on start
		this.addWindowListener(listener); // add listener for this window

		orderPanel.setPreferredSize(orderPanel.getSize());
		
		// add panels for this window
		this.add(selectionPanel, BorderLayout.CENTER);
		this.add(orderPanel, BorderLayout.EAST);
	}
}
