package s24109.onlinestore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@IdClass(CartItemId.class)
public class CartItem {

    @Id
    @ManyToOne
    private UserOrder userOrder;
    @Id
    @ManyToOne
    private Product product;
    @Min(value = 1, message = "Item quantity must be above zero.")
    private Integer quantity;

    public CartItem(Product product, UserOrder userOrder, int quantity) {
        this.product = product;
        this.userOrder = userOrder;
        this.quantity = quantity;
    }

    public CartItem() {

    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userOrder=" + userOrder +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

}
