package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Till;

/**
 * Menu bar for the main window of the program. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class MainMenu extends JMenuBar
{
	private MainMenuListener listener;
	public MainMenu(HistoryFrame historyFrame, ItemsFrame itemsFrame, Till till)
	{
		// listener
		listener = new MainMenuListener(historyFrame, itemsFrame, till);
		
		// menus
		JMenu file = new JMenu("File");
		JMenu admin = new JMenu("Admin");
		JMenu help = new JMenu("Help");

		// menu items
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem tills = new JMenuItem("Till History");
		JMenuItem items = new JMenuItem("Edit Menu");
		JMenuItem about = new JMenuItem("About");
		
		// add menu items to menus
		file.add(exit);
		admin.add(tills);
		admin.add(items);
		help.add(about);
		
		// add listeners to menu items
		exit.addActionListener(listener);
		tills.addActionListener(listener);
		items.addActionListener(listener);
		about.addActionListener(listener);
		
		// add menus to menu bar
		this.add(file);
		this.add(admin);
		this.add(help);
	}
}
