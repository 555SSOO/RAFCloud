package rs.raf.cloud.controllers;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.cloud.controllers.constants.ControllerConstants;
import rs.raf.cloud.entities.Machine;
import rs.raf.cloud.services.MachineService;
import rs.raf.cloud.services.UserService;

import java.util.List;


@RestController
@CrossOrigin(origins = ControllerConstants.ANGULAR_URL)
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    @Getter
    UserService userService;

    @Autowired
    @Getter
    MachineService machineService;

    @RequestMapping("/start")
    String startMachineByUID(@RequestParam("machineUID") String UID) {
        return "";
        //getMachineService().startMachine(UID);
        // Transfers to the interceptor, this just returns 200
    }

    @RequestMapping("/stop")
    String stopMachineByUID(@RequestParam("machineUID") String UID) {
        return "";
//        getMachineService().stopMachine(UID);
    }

    @RequestMapping("/restart")
    String restartMachineByUID(@RequestParam("machineUID") String UID) {
        return "";
//        getMachineService().restartMachine(UID);
    }

    @RequestMapping("/create")
    void createMachine(@RequestParam("active") String active,
                       @RequestParam("machineName") String machineName,
                       @RequestParam("username") String username) {
        getMachineService().createMachine(Boolean.parseBoolean(active), machineName, username);
    }

    @RequestMapping("/destroy")
    void destroyMachineByUID(@RequestParam("machineUID") String UID) {
        getMachineService().destroyMachine(UID);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Machine> searchMachine(@RequestParam("machineName") String machineName,
                                @RequestParam("status") String status,
                                @RequestParam("dateFrom") String dateFrom,
                                @RequestParam("dateTo") String dateTo) {
        return getMachineService().searchMachine(machineName, status, dateFrom, dateTo);
    }

}
