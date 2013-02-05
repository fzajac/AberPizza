package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Listener for the menu in main window. 
 * @author Filip Zajac (fiz)
 * @see MainMenu
 */
public class MainMenuListener implements ActionListener
{
	private HistoryFrame historyFrame;
	private ItemsFrame itemsFrame;
	private Till till; 
	
	public MainMenuListener(HistoryFrame hF, ItemsFrame iT, Till t)
	{
		historyFrame = hF;
		itemsFrame = iT;
		till = t; 
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String event = e.getActionCommand();
		if(event.equals("Exit"))
		{
			// save & exit
			till.save();
			System.exit(0);
		}
		else if (event.equals("Till History"))
		{
			// display HistoryFrame
			historyFrame.showHistoryFrame();
		}
		else if (event.equals("Edit Menu"))
		{
			// display ItemsFrame
			itemsFrame.showItemsFrame();
		}
		else if(event.equals("About"))
			JOptionPane.showMessageDialog(null, "AberPizza assignment by Filip Zajac", "AberPizza - About", JOptionPane.INFORMATION_MESSAGE);
	}

}
