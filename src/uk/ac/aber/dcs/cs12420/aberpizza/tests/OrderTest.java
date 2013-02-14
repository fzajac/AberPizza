package uk.ac.aber.dcs.cs12420.aberpizza.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Type;

/**
 * Test methods for Order class. 
 * @author FZajac
 * @see Order
 */
public class OrderTest 
{
	/**
	 * Test setDate() and getDate() methods. 
	 */
	@Test
	public void testSetAndGetDate() 
	{
		Date date = new Date();
		Order order = new Order(); 
		order.setDate(date);
		assertEquals("Incorrect date", date, order.getDate());
	}
	
	/**
	 * Test setCustomerName() and getCustomerName() methods. 
	 */
	@Test
	public void testSetAndGetCustomerName()
	{
		Order order = new Order(); 
		order.setCustomerName("Bob");
		assertEquals("Incorrect customer's name", "Bob", order.getCustomerName());
	}
	
	/**
	 * Test setItems() and getItems() methods. 
	 */
	@Test
	public void testSetAndGetItems()
	{
		Order order = new Order(); 
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		items.add(new OrderItem(new GeneralItem(new BigDecimal("2.20"), "item 0"), 2));
		items.add(new OrderItem(new GeneralItem(new BigDecimal("3.59"), "item 1"), 5));
		order.setItems(items);
		assertEquals("Incorrect items", items, order.getItems());
	}
	
	/**
	 * Test setTender() and getTender() methods. 
	 */
	@Test
	public void testSetAndGetTender()
	{
		Order order = new Order();
		order.setTender(new BigDecimal("30.00"));
		assertEquals("Incorrect tender", new BigDecimal("30.00"), order.getTender());
	}
	
	/**
	 * Test setChange() and getChange() methods. 
	 */
	@Test
	public void testSetAndGetChange()
	{
		Order order = new Order();
		order.setChange(new BigDecimal("2.01"));
		assertEquals("Incorrect change", new BigDecimal("2.01"), order.getChange());
	}
	
	/**
	 * Test addItem() and getItem() methods. 
	 */
	@Test
	public void testAddAndGetItem()
	{
		Order order = new Order();
		GeneralItem item = new GeneralItem(new BigDecimal("4.50"), "Test", Type.DRINK);
		order.addItem(item, 1);
		assertEquals("Incorrect item", item, order.getItem(0).getItem());
	}
	
	/**
	 * Test addItem() with adding the same item twice. 
	 */
	@Test
	public void testAddSameItems()
	{
		Order order = new Order();
		GeneralItem item = new GeneralItem(new BigDecimal("4.50"), "Test", Type.DRINK);
		order.addItem(item, 1);
		order.addItem(item, 2);
		assertEquals("Incorrect quantity", 3, order.getItem(0).getQuantity());
	}
	
	/**
	 * Test removeItem() method. 
	 */
	@Test
	public void testRemoveItem()
	{
		Order order = new Order();
		order.addItem(new GeneralItem(), 1);
		order.removeItem(0);
		assertEquals("Incorrect number of items", 0, order.getItems().size());
	}
	
	/**
	 * Test updateItemQuantity() method. 
	 */
	@Test
	public void testUpdateItemQuantity()
	{
		Order order = new Order();
		GeneralItem item = new GeneralItem(new BigDecimal("4.50"), "Test", Type.DRINK);
		order.addItem(item, 2);
		order.updateItemQuantity(item, 5);
		assertEquals("Incorrect quantity", 5, order.getItem(0).getQuantity());
	}
	
	/**
	 * Test getSubtotal() method. 
	 */
	@Test
	public void testGetSubtotal()
	{
		Order order = new Order();
		GeneralItem item = new GeneralItem(new BigDecimal("4.50"), "Test", Type.DRINK);
		order.addItem(item, 2);
		assertEquals("Incorrect subtotal", new BigDecimal("9.00"), order.getSubtotal());
	}
	
	/**
	 * Test getDiscount() method. 
	 */
	@Test 
	public void testGetDiscount()
	{
		Order order = new Order();
		GeneralItem item = new GeneralItem(new BigDecimal("4.50"), "Test", Type.PIZZA);
		order.addItem(item, 2);
		GeneralItem item3 = new GeneralItem(new BigDecimal("0.99"), "Cola", Type.DRINK);
		order.addItem(item3, 1);
		assertEquals("Incorrect discount", new BigDecimal("0.99"), order.getDiscount());
	}
}
