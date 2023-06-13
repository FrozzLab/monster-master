package s24109.onlinestore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import s24109.onlinestore.DAL.ProductRepository;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

}
