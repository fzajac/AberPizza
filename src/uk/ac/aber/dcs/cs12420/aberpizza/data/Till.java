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
 * Holds all the past orders, loading and saving them using XMLs. 
 * @author Filip Zajac (fiz)
 */
public class Till 
{
	/**
	 * ArrayList of all the orders
	 */
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public Till()
	{
		
	}
	
	/**
	 * Adds specified order to the till (list of orders) 
	 * @param order
	 */
	public void addOrder(Order order)
	{
		Order theOrder = new Order(order.getCustomerName(), order.getItems(), order.getDate(), order.getTender(), order.getChange());
		orders.add(theOrder);
	}
	
	/**
	 * Gets list of orders
	 * @return list of orders
	 */
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	/**
	 * Gets sum of all the orders
	 * @return sum of all orders
	 */
	public BigDecimal getTotalForDay()
	{
		BigDecimal total = new BigDecimal("0.00");
		for(int i = 0; i < orders.size(); i++)
		{
			total = total.add(orders.get(i).getSubtotal());
		}
		return total;
	}
	
	/**
	 * Saves list of orders to Till.xml file. 
	 */
	public void save() 
	{
		try
		{
			FileOutputStream file = new FileOutputStream("Till.xml"); 
			BufferedOutputStream buffered = new BufferedOutputStream(file); 
			XMLEncoder encoder = new XMLEncoder(buffered);
			
			//This will serialise BigDecimal values: 
			PersistenceDelegate pd=encoder.getPersistenceDelegate(Integer.class); 
			encoder.setPersistenceDelegate(BigDecimal.class,pd );
			
			encoder.writeObject(orders);
			encoder.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load list of orders from Till.xml file. 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Till load() throws IOException
	{
		Till till = new Till();
		FileInputStream file = new FileInputStream("Till.xml");
		XMLDecoder decoder = new XMLDecoder(file);
		till.orders = (ArrayList<Order>)decoder.readObject();
		decoder.close();
		return till; 
	}
}