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
    private Long id;
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;

    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters long.")
    private String username;
    @Email(message = "Value must be a valid email.")
    private String email;

    @Password(message = "Password must be between 8 and 20 characters long, have both " +
            "lower- and uppercase characters, and at least one digit and special character")
    private String password;
    @OneToMany(mappedBy = "shopUser")
    private Set<UserOrder> userOrders = new HashSet<>();
    @OneToMany(mappedBy = "shopUser")
    private Set<Product> products = new HashSet<>();

    public ShopUser(String firstName, String lastName,
                    String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public ShopUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

}
