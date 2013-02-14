package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;

/**
 * Every single item in the order. 
 * @author Filip Zajac (fiz)
 */
public class OrderItem 
{
	/**
	 * Quantity of the item. 
	 */
	private int quantity = 0; 
	
	/**
	 * Takes an Item (GeneralItem) to OrderItem. 
	 * @see Item
	 * @see GeneralItem
	 */
	private Item item = new GeneralItem(); 
	
	public OrderItem()
	{
		
	}
	
	/**
	 * Use to create OrderItem with specified item and quantity. 
	 * @param item 
	 * @param quantity quantity of the item in order
	 */
	public OrderItem(Item item, int quantity)
	{
		this.item = (GeneralItem) item;
		this.quantity = quantity;
	}
	
	/**
	 * Gets item for the OrderItem. 
	 * @return item item for the OrderItem
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Sets item for the OrderItem. 
	 * @param item item for the OrderItem. 
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
	/**
	 * Gets price. 
	 * @return price
	 */
	public BigDecimal getPrice()
	{
		return item.getPrice();
	}
	
	/**
	 * Sets price. 
	 * @param p price
	 */
	public void setPrice(BigDecimal p)
	{
		item.setPrice(p);
	}
	
	/**
	 * Gets quantity. 
	 * @return quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * Sets quantity; 
	 * @param q quantity
	 */
	public void setQuantity(int q)
	{
		quantity = q;
	}
	
	/**
	 * Gets total price for this item (price of single item multiplied by quantity). 
	 * @return total price
	 */
	public BigDecimal getOrderItemTotal()
	{
		return item.getPrice().multiply(new BigDecimal(quantity));
	}
}
