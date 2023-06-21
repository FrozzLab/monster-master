package s24109.onlinestore.DAL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24109.onlinestore.models.ShopUser;

@Repository
public interface UserRepository extends JpaRepository<ShopUser, Long> {

    ShopUser findByUsername(String username);

}
