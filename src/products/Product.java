package products;

import shopping.ItemElement;

public abstract class Product implements ItemElement{

	private int ID;
	private String name;
	private double price;
	
	public Product(int ID, String name, double price){
		this.ID = ID;
		this.name = name;
		this.price = price;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return "ID : "+ID+", name : "+name+", Price"+price+", ";
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param set the ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param set price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Add sort info into the result String in order to parse Csv data.
	 * @return Csv string.
	 */
	public abstract String getCsvStr();
}