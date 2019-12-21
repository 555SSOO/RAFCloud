package rs.raf.chillflix.services.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rs.raf.chillflix.entities.Customer;
import rs.raf.chillflix.entities.Product;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.entities.UserProductData;
import rs.raf.chillflix.repository.CustomerRepository;
import rs.raf.chillflix.repository.ProductRepository;
import rs.raf.chillflix.repository.UserProductDataRepository;
import rs.raf.chillflix.repository.UserRepository;
import rs.raf.chillflix.services.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CfProductService implements ProductService {

    @Autowired
    @Getter
    ProductRepository productRepository;

    @Autowired
    @Getter
    UserRepository userRepository;

    @Autowired
    @Getter
    UserProductDataRepository userProductDataRepository;

    @Override
    public List<Product> getAllProductsForUser(User user){
        List<UserProductData> userProductDataList = getUserProductDataRepository().findUserProductDataByUser(user);
        return userProductDataList.stream().map(UserProductData::getProduct).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts(){
        return (List<Product>) getProductRepository().findAll();
    }

    @Override
    public void setVideoIdsOnProduct(Long productId, String videoId){
        getProductRepository().findById(productId).ifPresent(product -> {
            product.addVideoId(videoId);
            getProductRepository().save(product);
        });
    }

    @Override
    public void updateTimeWatched(int timestamp, Long productId, Long userId){
        UserProductData userProductData = getUserProductData(productId, userId);
        userProductData.setTimestamp(timestamp);
        getUserProductDataRepository().save(userProductData);
    }

    @Override
    public int getTimeWatched(Long productId, Long userId){
        return getUserProductData(productId, userId).getTimestamp();
    }

    private UserProductData getUserProductData(Long productId, Long userId){
        final UserProductData[] userProductData = {null};
        getProductRepository().findById(productId).ifPresent(product -> {
            getUserRepository().findById(userId).ifPresent(user -> {
                    List<UserProductData> userProductDataList = getUserProductDataRepository().findUserProductDataByUserAndProduct(user, product);
                    if(!CollectionUtils.isEmpty(userProductDataList)){
                        userProductData[0] = userProductDataList.get(0);
                    } else {
                        userProductData[0] = new UserProductData(user, product);
                    }
            });
        });
        return userProductData[0];
    }

}
