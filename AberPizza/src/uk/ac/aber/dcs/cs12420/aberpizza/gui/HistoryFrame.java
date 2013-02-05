package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JFrame;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Window holding history of orders. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class HistoryFrame extends JFrame
{
	/**
	 * Panel for this window. 
	 */
	private HistoryPanel panel;
	
	public HistoryFrame(Till till)
	{
		panel = new HistoryPanel(till);
		
		this.setSize(700, 450); // set frame size
		this.setLocationRelativeTo(null); // position the frame (center)
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // hide the window on close operation
		this.setTitle("AberPizza - Till History"); //frame's title
		this.setVisible(false); // set invisible at start
		
		this.add(panel); // add the HistoryPanel
	}
	
	/**
	 * Updates the list of orders and sets history window to visible. 
	 */
	public void showHistoryFrame()
	{
		panel.updateList();
		this.setVisible(true);
	}
}