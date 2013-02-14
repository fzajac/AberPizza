package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Holds a single order. 
 * @author Filip Zajac (fiz)
 */
public class Order 
{
	/**
	 * ArrayList of items in Order (OrderItem type). 
	 */
	private ArrayList<OrderItem> items = new ArrayList<OrderItem>();

	/**
	 * Date of order. 
	 */
	private Date date = new Date();

	/**
	 * Name of customer taking the order. 
	 */
	private String customerName = ""; 

	/**
	 * Money that customer gave to the cashier. 
	 */
	private BigDecimal tender = new BigDecimal("0.00"); 

	/**
	 * Change that cashier should give to the customer.
	 */
	private BigDecimal change = new BigDecimal("0.00"); 
	
	public Order()
	{
		
	}
	
	/**
	 * Use this constructor when adding orders to the Till.
	 * @param cName customer's name
	 * @param i ArrayList of items (orderItem type)
	 * @param d date of order
	 * @param t tender
	 * @param c change
	 * @see Till
	 */
	public Order(String cName, ArrayList<OrderItem> i, Date d, BigDecimal t, BigDecimal c)
	{
		customerName = cName; 
		items = i;
		date = d;
		tender = t; 
		change = c;
	}
	
	/**
	 * Sets the date of the order.
	 * @param d date of the order
	 */
	public void setDate(Date d)
	{
		date = d;
	}
	
	/**
	 * Gets the date of the order.
	 * @return date of the order
	 */
	public Date getDate()
	{
		return date; 
	}

	/**
	 * Sets customer name for the order.
	 * @param name customer's name
	 */
	public void setCustomerName(String name)
	{
		customerName = name; 
	}
	
	/**
	 * Gets customer name for the order.
	 * @return Customer's name
	 */
	public String getCustomerName()
	{
		return customerName;
	}
	
	/**
	 * Sets items ArrayList for the order.
	 * @param i items of the ArrayList<OrderItem>
	 * @see OrderItem list of ordered items
	 */
	public void setItems(ArrayList<OrderItem> i)
	{
		items = i; 
	}
	
	/**
	 * Gets items ArrayList for the order.
	 * @return list of ordered items
	 */
	public ArrayList<OrderItem> getItems()
	{
		return items; 
	}
	
	/**
	 * Sets the amount of money given to cashier (tender).
	 * @param tender money given to cashier
	 */
	public void setTender(BigDecimal tender) {
		this.tender = tender;
	}
	
	/**
	 * Gets the amount of money given to cashier (tender).
	 * @return money given to cashier
	 */
	public BigDecimal getTender() {
		return tender;
	}
	
	/**
	 * Sets change for the order.
	 * @param change 
	 */
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	
	/**
	 * Gets change for the order.
	 * @return change
	 */
	public BigDecimal getChange() {
		return change;
	}
	
	/**
	 * Adds an item to the order
	 * @param item
	 * @param quantity
	 */
	public void addItem(Item item, int quantity)
	{
		boolean found = false;
		// if the item already is on the list, increase its quantity: 
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getItem().getDescription().equals(item.getDescription()))
			{
				items.get(i).setQuantity(items.get(i).getQuantity() + quantity);
				found = true;
				break;
			}
		}
		// else, add new item: 
		if(found == false)
			items.add(new OrderItem(item, quantity));
	}
	
	/**
	 * Gets a single order item. 
	 * @param i index of the item
	 * @return item from the items ArrayList
	 */
	public OrderItem getItem(int i)
	{
		return items.get(i);
	}
	
	/**
	 * Removes an item from the order.
	 * @param i index of the item to remove
	 */
	public void removeItem(int i)
	{
		items.remove(i);
	}
	
	/**
	 * Gets an Object[][] containing items from the list of order items for the current order's JTable. 
	 * @return Object[][] containing order items
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.gui.OrderPanel
	 */
	public Object[][] getItemsArray()
	{
		Object [][] result = new Object[items.size()][3];
		for(int i = 0; i < items.size(); i++)
		{
			result[i][0] = items.get(i).getItem().getDescription();
			result[i][1] = items.get(i).getPrice();
			result[i][2] = items.get(i).getQuantity();
		}
		return result;
	}
	
	/**
	 * Updates quantity of specified item.
	 * @param item item to be updated
	 * @param quantity new quantity
	 */
	public void updateItemQuantity(Item item, int quantity)
	{
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getItem().getDescription() == item.getDescription())
				items.get(i).setQuantity(quantity);
		}
	}
	
	/**
	 * Gets subtotal for the order. 
	 * @return subtotal
	 */
	public BigDecimal getSubtotal()
	{
		BigDecimal subtotal = new BigDecimal("0.00");
		for(int i = 0; i < items.size(); i++)
				subtotal = subtotal.add(items.get(i).getOrderItemTotal());
		subtotal = subtotal.subtract(getDiscount());
		return subtotal;
	}
	
	/**
	 * Gets discount for the order.
	 * @return discount
	 */
	public BigDecimal getDiscount()
	{
		//change the variable below to represent actual number of discounts
		int numberOfDiscounts = 2; 
		
		BigDecimal discount = new BigDecimal("0.00");
		BigDecimal[] discounts = new BigDecimal[numberOfDiscounts];
		
		//DISCOUNT #0: if you get 3 or more pizzas, 1 of them is free
		discounts[0] = new BigDecimal("0.00");
		int d0numberOfPizzas = 0;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getItem().getType().equals(Type.PIZZA))
				d0numberOfPizzas += items.get(i).getQuantity();
			if(d0numberOfPizzas >= 3)
				break;
		}
		if(d0numberOfPizzas >= 3)
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getItem().getType().equals(Type.PIZZA))
				{
					if(discounts[0].equals(new BigDecimal("0.00")))
						discounts[0] = items.get(i).getPrice();
					else if(items.get(i).getPrice().compareTo(discounts[0]) == -1)
						discounts[0] = items.get(i).getPrice();
				}
			}
		}
		
		//DISCOUNT #1: if you get 2 or more pizzas and cola, one cola is free
		discounts[1] = new BigDecimal("0.00");
		int d1numberOfPizzas = 0;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).getItem().getType().equals(Type.PIZZA))
				d1numberOfPizzas += items.get(i).getQuantity();
			if(d1numberOfPizzas >= 2)
				break;
		}
		if(d1numberOfPizzas >= 2)
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getItem().getDescription().equals("Cola"))
					discounts[1] = items.get(i).getPrice();
			}
		}
		
		for(int i = 0; i < discounts.length; i++)
		{
			if(discounts[i].compareTo(discount) == 1)
				discount = discounts[i];
		}
		
		return discount;
	}
	
	/**
	 * Gets the receipt for the order
	 * @return receipt for the order
	 */
	public String getReceipt()
	{
		StringBuilder s = new StringBuilder();
		
		s.append("<center><span style='font-family: Courier New;'>AberPizza</span></center>");
		s.append("<center><span style='font-family: Courier New;'>(contact informations here)</span></center>");
		s.append("<center><span style='font-family: Courier New;'>----------------------------------</span></center>");
		s.append("<span style='font-family: Courier New;'>Customer: " + getCustomerName() + "</span><br />");
		s.append("<span style='font-family: Courier New;'>Date: " + getDate() + "</span><br />");
		s.append("<center><span style='font-family: Courier New;'>----------------------------------</span></center>");
		s.append("<table style='font-family: Courier New; width: 100%;'>");
		for(int i = 0; i < items.size(); i++)
			s.append("<tr><td>" + items.get(i).getItem().getDescription() + "</td><td style='text-align: right;'>" + items.get(i).getQuantity() + " x " + items.get(i).getPrice() + "</td></tr>");
		s.append("</table>");
		s.append("<center><span style='font-family: Courier New;'>----------------------------------</span></center>");
		s.append("<table style='font-family: Courier New; width: 100%;'>");
		s.append("<tr><td></td><td style='text-align: right;'>Subtotal: " + getSubtotal() + "</td></tr>");
		s.append("<tr><td></td><td style='text-align: right;'>Discount: " + getDiscount() + "</td></tr>");
		s.append("<tr><td></td><td style='text-align: right;'>Tender: " + getTender() + "</td></tr>");
		s.append("<tr><td></td><td style='text-align: right;'>Change: " + getChange() + "</td></tr>");
		s.append("</table>");
		
		return s.toString(); 
	}
	
	/**
	 * Creates a new, empty ArrayList for items in order. 
	 */
	public void clear()
	{
		items = new ArrayList<OrderItem>();
	}
}
