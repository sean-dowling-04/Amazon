package shopping;

import products.Book;
import products.Phone;


public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
 
    @Override
    public double visit(Book book) {
        double cost=0;
        //apply 5Eur discount if book price is greater than 50
        if(book.getPrice() > 50){
            cost = book.getPrice()-5;
        }else cost = book.getPrice();
        return cost;
    }
 
    @Override
    public double visit(Phone phone) {
        double cost=0;
        //apply 10Eur discount if book price is greater than 50
        if(phone.getPrice() > 50){
            cost = phone.getPrice()-10;
        }else cost = phone.getPrice();
        return cost;
    }
}