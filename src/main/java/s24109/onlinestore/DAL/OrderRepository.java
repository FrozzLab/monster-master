package s24109.onlinestore.DAL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24109.onlinestore.models.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

}
