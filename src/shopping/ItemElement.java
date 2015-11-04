package shopping;
public interface ItemElement {
 
    public double accept(ShoppingCartVisitor visitor);
}