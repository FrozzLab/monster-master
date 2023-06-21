package s24109.onlinestore.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import s24109.onlinestore.DAL.UserRepository;
import s24109.onlinestore.models.ShopUser;
import s24109.onlinestore.security.ShopUserDetails;

@Service
public class ShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new ShopUserDetails(user);
    }

}
