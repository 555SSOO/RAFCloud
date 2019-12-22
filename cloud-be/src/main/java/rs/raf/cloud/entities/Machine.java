package rs.raf.cloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.cloud.util.constants.MachineStatus;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="raf_machine")
@Getter @Setter @NoArgsConstructor
public class Machine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String UID;
    @Column(nullable = false)
    private MachineStatus status;
    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raf_user_id", nullable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public Machine(String UID, String name, MachineStatus status, boolean active) {
        this.UID = UID;
        this.name = name;
        this.status = status;
        this.active = active;
        this.creationDate = new Date();
    }
}
