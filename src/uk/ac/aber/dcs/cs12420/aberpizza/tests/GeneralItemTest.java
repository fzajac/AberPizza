package uk.ac.aber.dcs.cs12420.aberpizza.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.cs12420.aberpizza.data.GeneralItem;
import uk.ac.aber.dcs.cs12420.aberpizza.data.Type;

/**
 * Test methods for GeneralItem class. 
 * @author Filip Zajac (fiz)
 * @see GeneralItem
 */
public class GeneralItemTest 
{
	/**
	 * Test setPrice() and getPrice() methods. 
	 */
	@Test
	public void testSetAndGetPrice() 
	{
		GeneralItem gI = new GeneralItem();
		gI.setPrice(new BigDecimal("29.32"));
		assertEquals("Incorrect price", new BigDecimal("29.32"), gI.getPrice());
	}
	
	/**
	 * Test setDescription() and getDescription() methods. 
	 */
	@Test
	public void testSetAndGetDescription() 
	{
		GeneralItem gI = new GeneralItem();
		gI.setDescription("Example Pizza");
		assertEquals("Incorrect description", "Example Pizza", gI.getDescription());
	}
	
	/**
	 * Test setType() and getType() methods. 
	 */
	@Test
	public void testSetAndGetType()
	{
		GeneralItem gI = new GeneralItem();
		gI.setType(Type.DRINK);
		assertEquals("Incorrect type", Type.DRINK, gI.getType());
	}
}
