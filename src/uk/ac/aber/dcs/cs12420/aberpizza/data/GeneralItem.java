package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * General item class. 
 * Implements Item interface. 
 * @author Filip Zajac (fiz)
 * @see Item
 */
public class GeneralItem implements Item
{
	/**
	 * Price of the item
	 */
	private BigDecimal price = new BigDecimal("0.00"); 
	
	/**
	 * Description of the item
	 */
	private String description = ""; 
	/**
	 * Type of the item; PIZZA, SIDE, or DRINK
	 * @see Type
	 */
	private Type type; 
	
	public GeneralItem()
	{
		
	}
	
	/**
	 * Use this constructor to automatically set the price and description of the item.
	 * @param price price of the item
	 * @param description description of the item
	 */
	public GeneralItem(BigDecimal price, String description)
	{
		this.price = price; 
		this.description = description; 
	}
	
	/**
	 * Use this constructor to automatically set the price, description and type of the item.
	 * @param price price of the item
	 * @param description description of the item
	 * @param type type of the item
	 * @see Type
	 */
	public GeneralItem(BigDecimal price, String description, Type type)
	{
		this.price = price; 
		this.description = description; 
		this.type = type; 
	}
	
	/**
	 * Gets price of the item
	 * @return Price of the item
	 */
	@Override
	public BigDecimal getPrice() {
		return price; 
	}
	
	/**
	 * Sets price of the item
	 * @param price price of the item
	 */
	@Override
	public void setPrice(BigDecimal price) {
		this.price = price; 
	}

	/**
	 * Gets the description of the item
	 * @return Description of the item
	 */
	@Override
	public String getDescription() {
		return description; 
	}

	/**
	 * Sets description of the item
	 * @param description description of the item
	 */
	@Override
	public void setDescription(String description) {
		this.description = description; 
	}

	/**
	 * Gets type of the item
	 * @return Type of the item
	 * @see Type
	 */
	@Override
	public Type getType() 
	{
		return type; 
	}

	/**
	 * Sets type of the item
	 * @param type type of the item
	 * @see Type
	 */
	@Override
	public void setType(Type type) 
	{
		this.type = type; 
	}
}
