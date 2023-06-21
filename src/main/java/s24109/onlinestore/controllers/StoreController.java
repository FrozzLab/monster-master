package s24109.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

}
