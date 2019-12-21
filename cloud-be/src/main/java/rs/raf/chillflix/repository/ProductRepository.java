package rs.raf.chillflix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.chillflix.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
