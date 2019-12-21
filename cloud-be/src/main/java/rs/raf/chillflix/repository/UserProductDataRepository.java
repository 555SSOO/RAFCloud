package rs.raf.chillflix.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.chillflix.entities.Product;
import rs.raf.chillflix.entities.User;
import rs.raf.chillflix.entities.UserProductData;

import java.util.List;

@Repository
public interface UserProductDataRepository extends CrudRepository<UserProductData, Long> {

    @Query("SELECT upd FROM UserProductData upd WHERE upd.user=:user")
    List<UserProductData> findUserProductDataByUser(User user);

    @Query("SELECT upd FROM UserProductData upd WHERE upd.user=:user AND upd.product=:product")
    List<UserProductData> findUserProductDataByUserAndProduct(User user, Product product);

}
