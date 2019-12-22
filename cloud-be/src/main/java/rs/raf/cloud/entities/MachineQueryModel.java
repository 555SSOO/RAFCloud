package rs.raf.cloud.entities;

import lombok.Data;
import rs.raf.cloud.util.constants.MachineStatus;

import java.util.Date;

@Data
public class MachineQueryModel {
    private String machineName;
    private MachineStatus status;
    private Date dateFrom;
    private Date dateTo;

    public MachineQueryModel(String machineName, MachineStatus status, Date dateFrom, Date dateTo) {
        this.machineName = machineName;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
