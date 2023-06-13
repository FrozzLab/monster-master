package s24109.onlinestore.seeder;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import s24109.onlinestore.DAL.CartRepository;
import s24109.onlinestore.DAL.OrderRepository;
import s24109.onlinestore.DAL.ProductRepository;
import s24109.onlinestore.DAL.UserRepository;
import s24109.onlinestore.models.Product;
import s24109.onlinestore.models.ShopUser;

@Component
public class Seeder implements ApplicationListener<ContextRefreshedEvent> {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Seeder(
            CartRepository cartRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private void seedData() {
        ShopUser zmundius = new ShopUser(
                "Zmundius",
                "Frigolodomus",
                "TotallyNotEvil",
                "zmundius@eberronmail.com",
                "TheGreatZmu__2nd"
        );

        ShopUser gruda = new ShopUser(
                "Gruda",
                "Brooda",
                "StoneSmasher",
                "bunga@barbmail.com",
                "ISmash333_Stones"
        );

        ShopUser peruvio = new ShopUser(
                "Peruvio",
                "Richius",
                "RichyRichmond",
                "rich@eberronmail.com",
                "heheImRich_1"
        );

        userRepository.save(zmundius);
        userRepository.save(gruda);
        userRepository.save(peruvio);

        Product kobold = new Product(
                zmundius,
                100.0,
                "Common Kobold",
                "A not-so-strong kobold, still effective as a sla-PAID WORKER WITH RIGHTS!!!",
                250
        );

        Product dragon = new Product(
                zmundius,
                1000000.0,
                "Dragon",
                "A DRAGON!!!!!!!",
                1
        );

        Product brigand = new Product(
                peruvio,
                1500.0,
                "Brigand",
                "A brigand, will do your dirty work, but probably won't die for you.",
                250
        );

        productRepository.save(kobold);
        productRepository.save(dragon);
        productRepository.save(brigand);

        zmundius.getProducts().add(kobold);
        zmundius.getProducts().add(dragon);
        peruvio.getProducts().add(brigand);

        userRepository.save(zmundius);
        userRepository.save(peruvio);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        seedData();
    }
}
