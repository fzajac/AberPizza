package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Items;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * Panel for selecting items to add to order. 
 * @author Filip Zajac (fiz)
 */
@SuppressWarnings("serial")
public class SelectionPanel extends JPanel
{
	private SelectionPanelListener listener;
	
	// navigation buttons 
	private JButton back = new JButton("Back");
	private JButton pizzas = new JButton("Pizzas");
	private JButton sides = new JButton("Sides");
	private JButton drinks = new JButton("Drinks");
	
	// ArrayLists of buttons with pizzas, sides, and drinks 
	private ArrayList<JButton> pizzaTypes = new ArrayList<JButton>();
	private ArrayList<JButton> sidesTypes = new ArrayList<JButton>();
	private ArrayList<JButton> drinksTypes = new ArrayList<JButton>();
	
	private Items items; 
	
	public SelectionPanel(MainFrame f, Items i, Order order, OrderPanel orderPanel)
	{
		items = i;
		
		// add listener
		listener = new SelectionPanelListener(this, order, orderPanel);
		back.addActionListener(listener);
		pizzas.addActionListener(listener);
		sides.addActionListener(listener);
		drinks.addActionListener(listener);

		// load the items from XML files
		try 
		{
			items.load();
		} 
		catch (IOException exception) 
		{
			items.loadAndSaveDefaults();
			JOptionPane.showMessageDialog(null, "XML files containing pizzas, sides, and drinks have not been found. \nDefaults have been loaded and XML files have been created. \nYou can edit them by going to \"Edit Menu\" in \"Admin\" menu. ", "XML loading error.", JOptionPane.WARNING_MESSAGE);
		}
		updateItems();
		
		this.setLayout(new GridLayout(0, 2, 2, 2)); // set layout
		this.setBorder(new EmptyBorder(2, 2, 2, 2)); // set border
		
		// set to mode 0 on start
		setMode(0);
	}
	
	/**
	 * Updates lists of pizzas, sides and drinks. 
	 */
	private void updateItems()
	{	
		updatePizzaTypes(items);
		updateSidesTypes(items);
		updateDrinksTypes(items);
	}
	
	/**
	 * Displays different content, depending on mode. 
	 * mode 0: main buttons; mode 1: pizza buttons; mode 2: sides buttons; mode 3: drinks buttons 
	 * @param mode
	 */
	public void setMode(int mode)
	{
		updateItems();
		
		this.removeAll(); // remove all existing buttons

		if (mode == 0) // add main buttons
		{
			this.add(pizzas);
			this.add(sides);
			this.add(drinks);
		}
		else if (mode == 1) // add pizza types buttons
		{
			for(int i = 0; i< pizzaTypes.size(); i++)
			{
				this.add(pizzaTypes.get(i));
			}
			this.add(back);
		}
		else if (mode == 2) // add sides types buttons
		{
			for(int i = 0; i< sidesTypes.size(); i++)
			{
				this.add(sidesTypes.get(i));
			}
			this.add(back);
		}
		else if (mode == 3) // add drinks types buttons
		{
			for(int i = 0; i< drinksTypes.size(); i++)
			{
				this.add(drinksTypes.get(i));
			}
			this.add(back);
		}
		else // lost? go back to main selection...
		{
			setMode(0);
		}

		// revalidate & repaint
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Update pizza buttons. 
	 * @param lI list of items
	 */
	private void updatePizzaTypes(Items lI)
	{
		pizzaTypes.removeAll(pizzaTypes);
		for(int i = 0; i < lI.getPizzas().size(); i++)
		{
			pizzaTypes.add(new JButton(lI.getPizzas().get(i).getDescription() + " - " + lI.getPizzas().get(i).getPrice()));
			pizzaTypes.get(i).addActionListener(listener);
		}
	}
	
	/**
	 * Update sides buttons. 
	 * @param lI list of items
	 */
	private void updateSidesTypes(Items lI)
	{
		sidesTypes.removeAll(sidesTypes);
		for(int i = 0; i < lI.getSides().size(); i++)
		{
			sidesTypes.add(new JButton(lI.getSides().get(i).getDescription() + " - " + lI.getSides().get(i).getPrice()));
			sidesTypes.get(i).addActionListener(listener);
		}
	}
	
	/**
	 * Update drinks buttons. 
	 * @param lI list of items
	 */
	private void updateDrinksTypes(Items lI)
	{
		drinksTypes.removeAll(drinksTypes);
		for(int i = 0; i < lI.getDrinks().size(); i++)
		{
			drinksTypes.add(new JButton(lI.getDrinks().get(i).getDescription() + " - " + lI.getDrinks().get(i).getPrice()));
			drinksTypes.get(i).addActionListener(listener);
		}
	}
}
