package s24109.onlinestore.DAL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24109.onlinestore.models.CartItem;
import s24109.onlinestore.models.CartItemId;

@Repository
public interface CartRepository extends JpaRepository<CartItem, CartItemId> {

}
