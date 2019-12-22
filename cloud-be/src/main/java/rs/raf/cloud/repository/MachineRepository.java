package rs.raf.cloud.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.raf.cloud.entities.Machine;

import javax.persistence.LockModeType;

@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Machine findByUID(String UID);
}
