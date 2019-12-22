package rs.raf.cloud.services;

import rs.raf.cloud.entities.Machine;

import java.util.List;

public interface MachineService {

    boolean startMachine(String UID);

    boolean stopMachine(String UID);

    boolean restartMachine(String UID);

    boolean createMachine(boolean active, String machineName, String username);

    boolean destroyMachine(String UID);

    List<Machine> searchMachine(String machineName, String status, String dateFrom, String dateTo);

}
