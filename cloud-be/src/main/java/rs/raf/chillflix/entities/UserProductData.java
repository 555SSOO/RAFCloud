package rs.raf.chillflix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cf_user_product_data")
@Getter @Setter @NoArgsConstructor
public class UserProductData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cf_user_id", nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cf_product_id", nullable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    public UserProductData(int timestamp) {
        this.timestamp = timestamp;
    }

    public UserProductData(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public UserProductData(int timestamp, User user, Product product) {
        this.timestamp = timestamp;
        this.user = user;
        this.product = product;
    }
}
