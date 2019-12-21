package rs.raf.chillflix.controllers;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.chillflix.controllers.constants.ControllerConstants;
import rs.raf.chillflix.entities.Product;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.services.ProductService;
import rs.raf.chillflix.services.UserService;

import java.util.List;


@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    @Getter
    UserService userService;

    @Autowired
    @Getter
    ProductService productService;

    @GetMapping(value = "/get-products-for-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Product> getAllProductsForUser(@RequestParam String userId) {
        try {
            User user = getUserService().getUserFromId(userId);
            return getProductService().getAllProductsForUser(user);
        } catch (Exception e) {
            LOG.info("UserService exception: " + e);
        }
        return null;
    }

    @GetMapping(value = "/get-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Product> getAllProducts() {
        return getProductService().getAllProducts();
    }

    @RequestMapping("/addVideo")
    String addVideo(@RequestParam("videoId") String videoId,
                    @RequestParam("productId")String productId){
        getProductService().setVideoIdsOnProduct(Long.valueOf(productId), videoId);
        return "ok";
    }

    @RequestMapping("/update-time-watched")
    void updateTimeWatched(@RequestParam("timeStamp") String timeStamp,
                             @RequestParam("productId") String productId,
                             @RequestParam("userId") String userId) {
        getProductService().updateTimeWatched(Integer.parseInt(timeStamp), Long.valueOf(productId), Long.valueOf(userId));
    }

    @RequestMapping("/get-time-watched")
    int getTimeWatched(@RequestParam("productId") String productId,
                           @RequestParam("userId") String userId) {
        return getProductService().getTimeWatched(Long.valueOf(productId), Long.valueOf(userId));
    }

}
