package s24109.onlinestore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue
    private Long id;
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    private ShopUser shopUser;
    @OneToMany(mappedBy = "userOrder")
    @NotEmpty(message = "Order must contain at least 1 item.")
    private Set<CartItem> items = new HashSet<>();
    private Double totalPrice;

}
