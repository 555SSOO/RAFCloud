package rs.raf.cloud.services.impl;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import rs.raf.cloud.controllers.MachineController;
import rs.raf.cloud.entities.Machine;
import rs.raf.cloud.entities.MachineQueryModel;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.exceptions.MachineException;
import rs.raf.cloud.repository.MachineRepository;
import rs.raf.cloud.repository.MachineSearchRepository;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.services.MachineService;
import rs.raf.cloud.util.constants.MachineOperation;
import rs.raf.cloud.util.constants.MachineStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class RafMachineService implements MachineService {

    private static final Logger LOG = LoggerFactory.getLogger(RafMachineService.class);

    @Autowired
    @Getter
    MachineRepository machineRepository;

    @Autowired
    @Getter
    MachineSearchRepository machineSearchRepository;

    @Autowired
    @Getter
    UserRepository userRepository;

    @Override
    public boolean startMachine(String UID) {
        try {
            changeMachineStatus(UID, MachineOperation.START);
            return true;
        } catch (InterruptedException | MachineException e) {
            return false;
        }
    }

    @Override
    public boolean stopMachine(String UID) {
        try {
            changeMachineStatus(UID, MachineOperation.STOP);
            return true;
        } catch (InterruptedException | MachineException e) {
            return false;
        }
    }

    @Override
    public boolean restartMachine(String UID) {
        try {
            changeMachineStatus(UID, MachineOperation.RESTART);
            return true;
        } catch (InterruptedException | MachineException e) {
            return false;
        }
    }

    private void changeMachineStatus(String UID, MachineOperation operation) throws InterruptedException, MachineException {
        Machine machine = getMachineRepository().findByUID(UID);
        if (Objects.nonNull(machine)) {

            switch (operation) {
                case START:
                    startMachine(machine);
                    return;
                case STOP:
                    stopMachine(machine);
                    return;
                case RESTART:
                    restartMachine(machine);
            }
        } else {
            LOG.error("No machine with the UID: " + UID);
            throw new MachineException("No machine with the UID: " + UID);
        }
    }

    private void startMachine(Machine machine) throws InterruptedException, MachineException {
        if (machine.getStatus().equals(MachineStatus.STOPPED)) {
            TimeUnit.SECONDS.sleep(10 + getRandomTimeDeviation());
            machine.setStatus(MachineStatus.RUNNING);
            getMachineRepository().save(machine);
        } else {
            LOG.error("Machine is in the wrong initial state: " + machine.getStatus());
            throw new MachineException("Machine is in the wrong initial state " + machine.getStatus());
        }
    }

    private void stopMachine(Machine machine) throws InterruptedException, MachineException {
        if (machine.getStatus().equals(MachineStatus.RUNNING)) {
            TimeUnit.SECONDS.sleep(10 + getRandomTimeDeviation());
            machine.setStatus(MachineStatus.STOPPED);
            getMachineRepository().save(machine);
        } else {
            LOG.error("Machine is in the wrong initial state: " + machine.getStatus());
            throw new MachineException("Machine is in the wrong initial state " + machine.getStatus());
        }
    }

    private void restartMachine(Machine machine) throws InterruptedException, MachineException {
        if (machine.getStatus().equals(MachineStatus.RUNNING)) {
            TimeUnit.SECONDS.sleep(5 + getRandomTimeDeviation());
            machine.setStatus(MachineStatus.STOPPED);
            getMachineRepository().save(machine);
            TimeUnit.SECONDS.sleep(5 + getRandomTimeDeviation());
            machine.setStatus(MachineStatus.RUNNING);
            getMachineRepository().save(machine);
        } else {
            LOG.error("Machine is in the wrong initial state: " + machine.getStatus());
            throw new MachineException("Machine is in the wrong initial state " + machine.getStatus());
        }
    }

    private int getRandomTimeDeviation() {
        return (new Random()).nextInt(3);
    }

    @Override
    public boolean createMachine(boolean active, String machineName, String username) {
        Machine machine = new Machine(generateUID(), machineName, MachineStatus.STOPPED, active);
        User user = getUserRepository().findByUsername(username);
        if (Objects.nonNull(user)) {
            user.addMachine(machine);
            getMachineRepository().save(machine);
            getUserRepository().save(user);
            return true;
        } else {
            LOG.error("Could not find the user with the username: " + username);
            return false;
        }

    }

    private String generateUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean destroyMachine(String UID) {
        Machine machine = getMachineRepository().findByUID(UID);
        if (Objects.nonNull(machine)) {
            machine.setStatus(MachineStatus.DELETED);
            getMachineRepository().save(machine);
            return true;
        } else {
            LOG.error("No machine with the UID: " + UID);
            return false;
        }
    }

    @Override
    public List<Machine> searchMachine(String machineName, String status, String dateFrom, String dateTo, String username) {

        if (StringUtils.isEmpty(machineName) && StringUtils.isEmpty(status) && StringUtils.isEmpty(dateFrom)
            && StringUtils.isEmpty(dateTo)) {
            return (List<Machine>) getMachineRepository().findAll();
        }
        return getMachineSearchRepository().searchMachines(buildMachineQueryModel(machineName, status, dateFrom, dateTo, username));

    }

    private MachineQueryModel buildMachineQueryModel(String machineName, String status, String dateFrom, String dateTo, String username) {
        MachineStatus machineStatus = null;
        Date machineDateFrom = null;
        Date machineDateTo = null;
        User user = null;
        if (!StringUtils.isEmpty(status)) {
            machineStatus = MachineStatus.valueOf(status);
        }
        if (!StringUtils.isEmpty(username)) {
            user = getUserRepository().findByUsername(username);
        }
        try {
            if (!StringUtils.isEmpty(dateFrom)) {
                machineDateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
            }
            if (!StringUtils.isEmpty(dateTo)) {
                machineDateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
            }
        } catch (ParseException e) {
            LOG.error("Wrong date format - " + e);
            return null;
        }
        return new MachineQueryModel(machineName,
                                     machineStatus,
                                     machineDateFrom,
                                     machineDateTo,
                                     user);
    }
}
