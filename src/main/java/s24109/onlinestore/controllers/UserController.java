package s24109.onlinestore.controllers;

import org.springframework.stereotype.Controller;
import s24109.onlinestore.DAL.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
