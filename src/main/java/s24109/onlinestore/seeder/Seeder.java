package s24109.onlinestore.seeder;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import s24109.onlinestore.DAL.ProductRepository;
import s24109.onlinestore.DAL.UserRepository;
import s24109.onlinestore.models.Product;
import s24109.onlinestore.models.ShopUser;

@Component
public class Seeder implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Seeder(ProductRepository productRepository,
                  UserRepository userRepository,
                  PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void seedData() {
        ShopUser zmundius = new ShopUser(
                "Zmundius",
                "Frigolodomus",
                "TotallyNotEvil",
                "zmundius@eberronmail.com",
                passwordEncoder.encode("TheGreatZmu__2nd"),
                "../user_img/1/pfp/zmundius.jpg",
                "An agent of chaos today, a broker of law tomorrow, 'tis the way of the world.",
                """
                        I am a great wizard, and I will be the greatest wizard in all of Eberron!
                        The world will bow before me, and I will rule over all! Mwaheheheh!
                        """
        );

        ShopUser gruda = new ShopUser(
                "Gruda",
                "Brooda",
                "StoneSmasher",
                "bunga@barbmail.com",
                passwordEncoder.encode("ISmash333_Stones"),
                "../user_img/2/pfp/rock.jpg",
                "Gruda smash! Gruda smash! Gruda smash!",
                """
                        Me Gruda, me smash! Me smash rocks, me smash heads, me smash everything!
                        Gruda smash! Gruda smash! Gruda smash!
                        """
        );

        ShopUser peruvio = new ShopUser(
                "Peruvio",
                "Richius",
                "RichyRichmond",
                "rich@eberronmail.com",
                passwordEncoder.encode("heheImRich_1"),
                "../user_img/3/pfp/peruvio.jpg",
                "I'm rich, you're not, get over it.",
                """
                        Rich, beautiful and smart - that's me. I'm the best, and you're not. Get over it. Or don't.
                        I don't care, I'm rich, and you're not. I'm RICH, and you're NOT. I'm RI-I-ICH, and you're NOT.
                        """
        );

        ShopUser admin = new ShopUser(
                "Sarnai",
                "Tyua",
                "MonsterMaster",
                "monster@master.com",
                passwordEncoder.encode("WhosTheCaptainNow"),
                "../user_img/4/pfp/Nephis.png",
                "I'm the captain now.",
                """
                        I am a warrior of love, beauty and justice. I will fight for the weak,
                         and I will protect the innocent. Oh, and I trade monsters on the side ;)
                        """
        );

        userRepository.save(zmundius);
        userRepository.save(gruda);
        userRepository.save(peruvio);
        userRepository.save(admin);

        Product kobold = new Product(
                zmundius,
                100.0,
                "Common Kobold",
                "A not-so-strong kobold, still effective as a sla-PAID WORKER WITH RIGHTS!!!",
                250,
                "../user_img/1/1/kobold.jpeg"
        );

        Product dragon = new Product(
                zmundius,
                1000000.0,
                "Dragon",
                "A DRAGON!!!!!!!",
                1,
                "../user_img/1/2/dragon.jpeg"
        );

        Product brigand = new Product(
                peruvio,
                1500.0,
                "Brigand",
                "A brigand, will do your dirty work, but probably won't die for you.",
                250,
                "../user_img/3/3/brigand.jpg"
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
