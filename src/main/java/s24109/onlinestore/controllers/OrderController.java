package s24109.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import s24109.onlinestore.DAL.CartRepository;
import s24109.onlinestore.DAL.OrderRepository;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/cart")
    public String getProducts(Model model) {
        return "cart";
    }

}
