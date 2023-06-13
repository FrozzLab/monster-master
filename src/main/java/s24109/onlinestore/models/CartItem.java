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

}
