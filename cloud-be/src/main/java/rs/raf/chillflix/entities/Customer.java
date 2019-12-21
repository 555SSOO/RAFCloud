package rs.raf.chillflix.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.chillflix.util.constants.SubscriptionType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cf_customer")
@Getter @Setter @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private SubscriptionType subscriptionType;

    @OneToMany(mappedBy="customer")
    @Column(nullable = false)
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    public Customer(String username, String password, SubscriptionType subscriptionType) {
        this.username = username;
        this.password = password;
        this.subscriptionType = subscriptionType;
    }

    public void addUser(User user) {
        users.add(user);
        user.setCustomer(this);
    }

}
