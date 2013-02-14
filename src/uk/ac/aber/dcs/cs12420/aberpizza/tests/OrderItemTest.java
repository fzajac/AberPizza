package uk.ac.aber.dcs.cs12420.aberpizza.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.OrderItem;

/**
 * Test methods for OrderItem class. 
 * @author Filip Zajac (fiz)
 * @see OrderItem
 */
public class OrderItemTest {
	
	/**
	 * Test setPrice() and getPrice() methods. 
	 */
	@Test
	public void testSetAndGetPrice() 
	{
		OrderItem orderItem = new OrderItem();
		orderItem.setPrice(new BigDecimal("2.50"));
		assertEquals("Incorrect price", new BigDecimal("2.50"), orderItem.getPrice());
	}
	
	/**
	 * Test setQuantity() and getQuantity() methods. 
	 */
	@Test
	public void testSetAndGetQuantity() 
	{
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(8);
		assertEquals("Incorrect quantity", 8, orderItem.getQuantity());
	}
	
	/**
	 * Test getOrderItemTotal() method. 
	 */
	@Test
	public void testGetOrderItemTotal()
	{
		OrderItem orderItem = new OrderItem();
		orderItem.setPrice(new BigDecimal("25.10"));
		orderItem.setQuantity(5);
		assertEquals("Incorrect total", new BigDecimal("125.50"), orderItem.getOrderItemTotal());
	}
	
	/**
	 * Test setItem() and getItem() methods. 
	 */
	@Test
	public void testSetAndGetItem()
	{
		OrderItem orderItem = new OrderItem();
		GeneralItem item = new GeneralItem(new BigDecimal("20.00"), "test");
		orderItem.setItem(item);
		assertEquals("Incorrect item", item, orderItem.getItem());
	}
}
