package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Listens to changes in selection of the list of orders in HistoryPanel.
 * @author Filip Zajac (fiz)
 * @see HistoryPanel
 */
public class HistoryListListener implements ListSelectionListener 
{
	private HistoryPanel historyPanel; 
	
	public HistoryListListener(HistoryPanel hP)
	{
		historyPanel = hP;
	}
	
	/**
	 * Calls updateSelected() method in HistoryPanel
	 * @see HistoryPanel#updateSelected()
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		historyPanel.updateSelected();
	}
}
