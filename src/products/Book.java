package products;

import shopping.ShoppingCartVisitor;

public class Book extends Product{

	private String author;
	private long ISBN;
	
	public Book(int ID, String name, double price, String author, long ISBN) {
		super(ID, name, price);
		this.author = author;
		this.ISBN = ISBN;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"author : "+author+", ISBN : "+ISBN;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param set author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the ISBN
	 */
	public long getISBN() {
		return ISBN;
	}

	/**
	 * @param set ISBN
	 */
	public void setISBN(long isbn) {
		ISBN = isbn;
	}

	public String getCsvStr() { return "Sort : Book, " + this.toString(); }
	
	public double accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}