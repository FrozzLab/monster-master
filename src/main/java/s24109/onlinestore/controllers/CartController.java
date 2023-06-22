package s24109.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import s24109.onlinestore.DAL.CartRepository;
import s24109.onlinestore.DAL.OrderRepository;
import s24109.onlinestore.DAL.ProductRepository;
import s24109.onlinestore.models.CartItem;
import s24109.onlinestore.models.Product;
import s24109.onlinestore.models.ShopUser;
import s24109.onlinestore.models.UserOrder;
import s24109.onlinestore.security.ShopUserDetails;

import java.util.Optional;
import java.util.UUID;

@Controller
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CartController(CartRepository cartRepository, ProductRepository productRepository,
                          OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/cart")
    public String getCart(Model model) {
        addUserCartAttributeToModel(model);

        return "cart";
    }

    @PostMapping("/cart/add/{productUuid}")
    public String addToCart(Model model, @PathVariable UUID productUuid, @RequestParam("quantity") int quantity) {
        if (!model.containsAttribute("user_cart")) {
            addUserCartAttributeToModel(model);
        }

        UserOrder userOrder = (UserOrder) model.getAttribute("user_cart");
        Optional<Product> optionalProduct = productRepository.findByUuid(productUuid);

        if (optionalProduct.isEmpty()) {
            throw new IllegalArgumentException("Product with uuid " + productUuid + " does not exist");
        }

        Product product = optionalProduct.get();
        Optional<CartItem> optionalCartItem = cartRepository.findByProductIdAndUserOrderId(product.getId(),
                                                                                           userOrder.getId());

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);

            cartRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem(product, userOrder, quantity);
            userOrder.getItems().add(cartItem);

            orderRepository.save(userOrder);
            cartRepository.save(cartItem);
        }

        return "redirect:/";
    }

    private void addUserCartAttributeToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ShopUserDetails shopUserDetails = (ShopUserDetails) auth.getPrincipal();
        ShopUser shopUser = shopUserDetails.getUser();

        Optional<UserOrder> optionalUserOrder =
                orderRepository.findByShopUserIdAndFinalized(shopUser.getId(), false);

        if (optionalUserOrder.isPresent()) {
            UserOrder userOrder = optionalUserOrder.get();
            model.addAttribute("user_cart", userOrder);
        }
        else {
            model.addAttribute("user_cart", new UserOrder(shopUser));
        }
    }

}
