package rs.raf.chillflix.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.entities.Product;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.entities.UserProductData;
import rs.raf.chillflix.repository.CustomerRepository;
import rs.raf.chillflix.repository.ProductRepository;
import rs.raf.chillflix.repository.UserProductDataRepository;
import rs.raf.chillflix.repository.UserRepository;
import rs.raf.chillflix.util.constants.Genre;
import rs.raf.chillflix.util.constants.SubscriptionType;
import rs.raf.chillflix.util.constants.Type;

import java.util.Arrays;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserProductDataRepository userProductDataRepository;

    @PostConstruct
    private void init() {

        User user1 = new User("Jack", "Bauer");
        User user2 = new User("Chloe", "O'Brian");
        User user3 = new User("Kim", "Bauer");
        User user4 = new User("David", "Palmer");
        User user5 = new User("Michelle", "Dessler");

        Customer customer1 = new Customer("customer1", "password1", SubscriptionType.PREMIUM);
        Customer customer2 = new Customer("customer2", "password2", SubscriptionType.BASE);
        Customer customer3 = new Customer("customer3", "password3", SubscriptionType.BASE);
        Customer customer4 = new Customer("customer4", "password4", SubscriptionType.BASE);

        Product product1 = new Product("movie1", Type.MOVIE, Genre.COMEDY);
        Product product2 = new Product("movie2", Type.MOVIE, Genre.COMEDY);
        Product product3 = new Product("movie3", Type.MOVIE, Genre.DRAMA);
        Product product4 = new Product("tvshow1", Type.TV_SHOW, Genre.COMEDY);

        UserProductData userProductData1 = new UserProductData(1);
        UserProductData userProductData2 = new UserProductData(2);
        UserProductData userProductData3 = new UserProductData(4);
        UserProductData userProductData4 = new UserProductData(3);
        UserProductData userProductData5 = new UserProductData(5);

        customer1.addUser(user1);
        customer1.addUser(user3);
        customer2.addUser(user2);
        customer3.addUser(user4);
        customer4.addUser(user5);

        user1.addUserProductData(userProductData1);
        user1.addUserProductData(userProductData2);
        user2.addUserProductData(userProductData3);
        user3.addUserProductData(userProductData4);
        user4.addUserProductData(userProductData5);

        product1.addUserProductData(userProductData1);
        product2.addUserProductData(userProductData2);
        product3.addUserProductData(userProductData3);
        product4.addUserProductData(userProductData4);
        product4.addUserProductData(userProductData5);

        getCustomerRepository().saveAll(Arrays.asList(customer1, customer2, customer3, customer4));
        getUserRepository().saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        getProductRepository().saveAll(Arrays.asList(product1, product2, product3, product4));
        getUserProductDataRepository().saveAll(Arrays.asList(userProductData1,
                                                        userProductData2,
                                                        userProductData3,
                                                        userProductData4,
                                                        userProductData5));
    }

    private CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    private UserRepository getUserRepository() {
        return userRepository;
    }

    private ProductRepository getProductRepository() {
        return productRepository;
    }

    private UserProductDataRepository getUserProductDataRepository() {
        return userProductDataRepository;
    }
}
