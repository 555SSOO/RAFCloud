package rs.raf.cloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.cloud.entities.Machine;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {
    Machine findByUID(String UID);
}
