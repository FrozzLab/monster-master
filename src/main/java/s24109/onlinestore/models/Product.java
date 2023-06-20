package s24109.onlinestore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();
    @ManyToOne
    @NotNull(message = "Product must have a seller.")
    private ShopUser shopUser;
    @NotNull(message = "Price cannot be blank.")
    @Min(value = 0, message = "Price cannot be negative.")
    private Double price;
    @NotBlank(message = "Product name cannot be blank.")
    private String name;
    private String description;
    @NotNull(message = "Product quantity cannot be null.")
    @Min(value = 0, message = "Product quantity cannot be negative.")
    private Integer quantity;
    private String imagePath;

    public Product() {

    }

    public Product(ShopUser shopUser, Double price, String name,
                   String description, Integer quantity, String imagePath) {
        this.shopUser = shopUser;
        this.price = price;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", shopUser=" + shopUser +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
