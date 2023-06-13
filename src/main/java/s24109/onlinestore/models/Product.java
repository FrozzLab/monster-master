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
    @GeneratedValue
    private UUID uuid;
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
    private String encodedImage;

}
