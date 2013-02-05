package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;

/**
 * Window holding availible items to add and remove. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class ItemsFrame extends JFrame
{
	private ItemsTabbedPane tabs; 
	public ItemsFrame(Items items)
	{
		tabs = new ItemsTabbedPane(items);
		
		this.setSize(500, 300); // set frame size
		this.setLocationRelativeTo(null); // position the frame (center)
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hide the window on close operation
		this.setTitle("AberPizza - Edit Menu"); //frame's title
		this.setVisible(false); // set invisible at start
		
		this.add(tabs); // add the tabs
	}
	
	/**
	 * Sets ItemsFrame to visible. 
	 */
	public void showItemsFrame()
	{
		this.setVisible(true);
	}
}
