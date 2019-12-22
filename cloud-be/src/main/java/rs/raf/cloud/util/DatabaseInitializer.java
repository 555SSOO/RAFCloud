package rs.raf.cloud.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.raf.cloud.entities.Machine;
import rs.raf.cloud.entities.User;
import rs.raf.cloud.repository.MachineRepository;
import rs.raf.cloud.repository.UserRepository;
import rs.raf.cloud.util.constants.MachineStatus;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    @Getter
    UserRepository userRepository;
    @Autowired
    @Getter
    MachineRepository productRepository;

    @PostConstruct
    private void init() {

        User user1 = new User("user1@user1.com", "password1", "firstName1", "lastName1");
        User user2 = new User("user2@user2.com", "password2", "firstName2", "lastName2");
        User user3 = new User("user3@user3.com", "password3", "firstName3", "lastName3");
        User user4 = new User("user4@user4.com", "password4", "firstName4", "lastName4");
        User user5 = new User("user5@user5.com", "password5", "firstName5", "lastName5");

        Machine machine1 = new Machine(generateUID(), "machine1", MachineStatus.STOPPED, true);
        Machine machine2 = new Machine(generateUID(), "machine2", MachineStatus.STOPPED, true);
        Machine machine3 = new Machine(generateUID(), "machine3", MachineStatus.STOPPED, true);
        Machine machine4 = new Machine(generateUID(), "machine4", MachineStatus.RUNNING, true);
        Machine machine5 = new Machine(generateUID(), "machine5", MachineStatus.STOPPED, true);

        user1.addMachine(machine1);
        user1.addMachine(machine2);
        user2.addMachine(machine2);
        user3.addMachine(machine3);
        user4.addMachine(machine4);
        user5.addMachine(machine5);

        getUserRepository().saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        getProductRepository().saveAll(Arrays.asList(machine1, machine2, machine3, machine4, machine5));

    }

    private String generateUID() {
        return UUID.randomUUID().toString();
    }

}
