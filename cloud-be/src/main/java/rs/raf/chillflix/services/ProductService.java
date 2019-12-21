package rs.raf.chillflix.services;

import rs.raf.chillflix.entities.Product;
import rs.raf.chillflix.entities.User;

import java.util.List;

public interface ProductService {

    List<Product> getAllProductsForUser(User user);

    List<Product> getAllProducts();

    void setVideoIdsOnProduct(Long productId, String videoId);

    void updateTimeWatched(int timestamp, Long productId, Long userId);

    int getTimeWatched(Long productId, Long userId);

}
