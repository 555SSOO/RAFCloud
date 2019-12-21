package rs.raf.chillflix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.chillflix.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
