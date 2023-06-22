package s24109.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import s24109.onlinestore.DAL.UserRepository;

import java.util.UUID;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users/{userUuid}")
    public String getProducts(Model model, @PathVariable UUID userUuid) {
        model.addAttribute("current_user", userRepository.findByUuid(userUuid));
        return "users";
    }

}
