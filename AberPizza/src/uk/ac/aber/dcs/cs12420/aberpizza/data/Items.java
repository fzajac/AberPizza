package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Holds ArrayLists of available pizzas, sides and drinks.
 * Loads and saves the ArrayLists using XML files (Pizzas.xml, Sides.xml, Drinks.xml). If the XML is not found, it adds default examples. 
 * @author Filip Zajac (fiz)
 *
 */
public class Items 
{
	/**
	 * ArrayList of pizzas
	 */
	private ArrayList<GeneralItem> pizzas = new ArrayList<GeneralItem>();
	
	/**
	 * ArrayList of sides
	 */
	private ArrayList<GeneralItem> sides = new ArrayList<GeneralItem>();
	
	/**
	 * ArrayList of drinks
	 */
	private ArrayList<GeneralItem> drinks = new ArrayList<GeneralItem>();
	
	public Items()
	{
		
	}
	
	/**
	 * Loads pizzas, sides, and drinks from XML files to ArrayLists
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException
	{
		XMLDecoder pDecoder = new XMLDecoder(new FileInputStream("Pizzas.xml"));
		pizzas = (ArrayList<GeneralItem>)pDecoder.readObject();
		pDecoder.close();
		XMLDecoder sDecoder = new XMLDecoder(new FileInputStream("Sides.xml"));
		sides = (ArrayList<GeneralItem>)sDecoder.readObject();
		sDecoder.close();
		XMLDecoder dDecoder = new XMLDecoder(new FileInputStream("Drinks.xml"));
		drinks = (ArrayList<GeneralItem>)dDecoder.readObject();
		dDecoder.close();
	}
	
	/**
	 * Adds example pizzas, sides, and drinks to ArrayLists, then saves to new XML files. 
	 * Should be used when XML files can not be found. 
	 * @see #save()
	 */
	public void loadAndSaveDefaults()
	{
		pizzas.add(new GeneralItem(new BigDecimal("3.99"), "Small Margheritta"));
		pizzas.add(new GeneralItem(new BigDecimal("5.99"), "Medium Margheritta"));
		pizzas.add(new GeneralItem(new BigDecimal("8.99"), "Large Margheritta"));
		pizzas.add(new GeneralItem(new BigDecimal("4.99"), "Small Pepperoni"));
		pizzas.add(new GeneralItem(new BigDecimal("6.99"), "Medium Pepperoni"));
		pizzas.add(new GeneralItem(new BigDecimal("9.99"), "Large Pepperoni"));
		pizzas.add(new GeneralItem(new BigDecimal("4.99"), "Small Hawaiian"));
		pizzas.add(new GeneralItem(new BigDecimal("6.99"), "Medium Hawaiian"));
		pizzas.add(new GeneralItem(new BigDecimal("9.99"), "Large Hawaiian"));
		sides.add(new GeneralItem(new BigDecimal("3.49"), "Garlic bread"));
		sides.add(new GeneralItem(new BigDecimal("1.69"), "Fries"));
		sides.add(new GeneralItem(new BigDecimal("1.99"), "Potato wedges"));
		sides.add(new GeneralItem(new BigDecimal("3.99"), "Coleslaw"));
		drinks.add(new GeneralItem(new BigDecimal("0.99"), "Cola"));
		drinks.add(new GeneralItem(new BigDecimal("0.99"), "Orange juice"));
		drinks.add(new GeneralItem(new BigDecimal("1.20"), "Lemonade"));
		save();
	}
	
	/**
	 * Saves pizzas, sides, drinks ArrayLists to Pizzas.xml, Sides.xml, and Drinks.xml files. 
	 */
	public void save()
	{
		try
		{
			XMLEncoder pEncoder = new XMLEncoder(new BufferedOutputStream( new FileOutputStream("Pizzas.xml")));
			PersistenceDelegate pdp=pEncoder.getPersistenceDelegate(Integer.class); 
			pEncoder.setPersistenceDelegate(BigDecimal.class,pdp );
			pEncoder.writeObject(pizzas);
            pEncoder.close();
            
			XMLEncoder sEncoder = new XMLEncoder(new BufferedOutputStream( new FileOutputStream("Sides.xml")));
			PersistenceDelegate pds=sEncoder.getPersistenceDelegate(Integer.class); 
			sEncoder.setPersistenceDelegate(BigDecimal.class,pds );
			sEncoder.writeObject(sides);
            sEncoder.close();
            
			XMLEncoder dEncoder = new XMLEncoder(new BufferedOutputStream( new FileOutputStream("Drinks.xml")));
			PersistenceDelegate pdd=pEncoder.getPersistenceDelegate(Integer.class); 
			dEncoder.setPersistenceDelegate(BigDecimal.class,pdd );
			dEncoder.writeObject(drinks);
            dEncoder.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Gets pizzas ArrayList. 
	 * @return ArrayList<GeneralItem> of pizzas
	 */
	public ArrayList<GeneralItem> getPizzas()
	{
		return pizzas;
	}

	/**
	 * Gets sides ArrayList.
	 * @return ArrayList<GeneralItem> of sides
	 */
	public ArrayList<GeneralItem> getSides()
	{
		return sides;
	}

	/**
	 * Gets drinks ArrayList.
	 * @return ArrayList<GeneralItem> of drinks
	 */
	public ArrayList<GeneralItem> getDrinks()
	{
		return drinks;
	}
}
