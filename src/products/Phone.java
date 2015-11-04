package products;

import shopping.ShoppingCartVisitor;

public class Phone extends Product{

	private String model;
	private String OS;
	
	public Phone(int ID, String name, double price, String model, String OS) {
		super(ID, name, price);
		this.model = model;
		this.OS = OS;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"model : "+model+", OS : "+OS;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param set model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the OS
	 */
	public String getOS() {
		return OS;
	}

	/**
	 * @param set OS
	 */
	public void setOS(String oS) {
		OS = oS;
	}

	public String getCsvStr() { return "Sort : Phone, " + this.toString(); }
	
	public double accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}