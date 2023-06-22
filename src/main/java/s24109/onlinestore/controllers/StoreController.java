package s24109.onlinestore.controllers;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import s24109.onlinestore.DAL.OrderRepository;
import s24109.onlinestore.DAL.ProductRepository;
import s24109.onlinestore.models.ShopUser;
import s24109.onlinestore.models.UserOrder;
import s24109.onlinestore.security.ShopUserDetails;

import java.util.Optional;

@Controller
public class StoreController {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public StoreController(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value={"/", "/home", "/index"})
    public String getMainPageData(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Model model) {
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
            return "redirect:/";
        }

        model.addAttribute("purchase", Boolean.FALSE);
        return "checkout";
    }

    @RequestMapping("/checkout-success")
    public String updatePurchaseValueAtCheckout(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ShopUserDetails shopUserDetails = (ShopUserDetails) auth.getPrincipal();
        ShopUser shopUser = shopUserDetails.getUser();

        Optional<UserOrder> optionalUserOrder =
                orderRepository.findByShopUserIdAndFinalized(shopUser.getId(), false);

        if (optionalUserOrder.isEmpty()) {
            return "redirect:/";
        }

        UserOrder userOrder = optionalUserOrder.get();
        userOrder.setFinalized(true);
        orderRepository.save(userOrder);

        model.addAttribute("user_cart", userOrder);
        model.addAttribute("purchase", Boolean.TRUE);
        return "checkout";
    }

}
