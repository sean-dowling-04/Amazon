package shopping;

import products.Phone;

public interface ShoppingCartVisitor {
 
    double visit(products.Book book);
    double visit(Phone phone);
}