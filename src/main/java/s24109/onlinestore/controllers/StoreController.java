package s24109.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import s24109.onlinestore.DAL.ProductRepository;

@Controller
public class StoreController {

    private final ProductRepository productRepository;

    @Autowired
    public StoreController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value={"/", "/home", "/index"})
    public String getMainPageData(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/checkout")
    public String getCheckoutScreen(Model model) {
        model.addAttribute("purchase", Boolean.FALSE);
        return "checkout";
    }

    @RequestMapping("/checkout-success")
    public String updatePurchaseValue(Model model) {
        model.addAttribute("purchase", Boolean.TRUE);
        return "checkout";
    }

}
