package s24109.onlinestore.DAL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s24109.onlinestore.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
