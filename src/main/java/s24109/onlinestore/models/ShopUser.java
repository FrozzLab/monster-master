package s24109.onlinestore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import s24109.onlinestore.constraints.Password;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class ShopUser {

    @Id
    @GeneratedValue
    private Double id;
    @GeneratedValue
    private UUID uuid;
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;
    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters long.")
    private String username;
    @Email(message = "Value must be a valid email.")
    private String email;
    @NotBlank(message = "Password cannot be blank.")
    @Password(message = "Password must be between 8 and 20 characters long, have both" +
            "lower- and uppercase characters, and at least one digit and special character")
    private String password;
    @OneToMany(mappedBy = "shopUser")
    private Set<UserOrder> userOrders = new HashSet<>();
    @OneToMany(mappedBy = "shopUser")
    private Set<Product> products = new HashSet<>();

}
