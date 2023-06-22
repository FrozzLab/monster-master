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
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();
    @ManyToOne
    private ShopUser shopUser;
    @OneToMany(mappedBy = "userOrder", fetch = FetchType.EAGER)
    private Set<CartItem> items = new HashSet<>();
    private Double totalPrice = 0.0;
    private Boolean finalized;

    public UserOrder(ShopUser shopUser) {
        this.shopUser = shopUser;
        this.finalized = false;
    }

    public UserOrder() {
        this.finalized = false;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getFinalized() {
        return finalized;
    }

    public void setFinalized(Boolean finalized) {
        this.finalized = finalized;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

}
