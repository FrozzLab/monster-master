package s24109.onlinestore.models;

import java.io.Serializable;
import java.util.Objects;

public class CartItemId implements Serializable {

    private UserOrder userOrder;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId that)) return false;
        return Objects.equals(userOrder, that.userOrder) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOrder, product);
    }
}
