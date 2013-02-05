package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;

/**
 * Tab containing list of available items of one type. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class ItemsTab extends JPanel
{
	private JList<Object> itemsJList;
	private ArrayList<GeneralItem> itemsList;
	private ItemsButtons buttons;
	public ItemsTab(ArrayList<GeneralItem> iL, Items items)
	{
		itemsList = iL;
		
		// set layout of this panel
		this.setLayout(new BorderLayout());
		
		buttons = new ItemsButtons(itemsList, items, this); 
		
		// create a JList and put it in a JScrollPane
		Object[] itemsArray = new Object[itemsList.size()];
		for(int i = 0; i < itemsList.size(); i++)
			itemsArray[i] = itemsList.get(i).getDescription() + " - " + itemsList.get(i).getPrice();
		itemsJList = new JList<Object>(itemsArray);
		JScrollPane scrollItems = new JScrollPane(itemsJList);
		
		// add components to this panel: 
		this.add(scrollItems, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.NORTH);
	}
	
	/**
	 * Update the list of items. 
	 */
	public void updateList()
	{
		Object[] itemsArray = new Object[itemsList.size()];
		for(int i = 0; i < itemsList.size(); i++)
			itemsArray[i] = itemsList.get(i).getDescription() + " - " + itemsList.get(i).getPrice();
		itemsJList.setListData(itemsArray);
	}
	
	/**
	 * Gets index of selected position on the list
	 * @return index of list selection 
	 */
	public int getListPosition()
	{
		return itemsJList.getSelectedIndex();
	}
}
