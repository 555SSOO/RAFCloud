package rs.raf.cloud.repository;

import org.springframework.stereotype.Repository;
import rs.raf.cloud.entities.Machine;
import rs.raf.cloud.entities.MachineQueryModel;

import java.util.List;

@Repository
public interface MachineSearchRepository {

    List<Machine> searchMachines(MachineQueryModel machineQueryModel);
}
