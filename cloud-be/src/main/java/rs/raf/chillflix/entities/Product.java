package rs.raf.chillflix.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.chillflix.util.constants.Genre;
import rs.raf.chillflix.util.constants.Type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cf_product")
@Getter @Setter @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Type type;
    private Genre genre;

    @ElementCollection
    @Column(name="cf_product_videoIds")
    private List<String> videoIds = new ArrayList<>();

    @OneToMany(mappedBy="product")
    @Column(nullable = false)
    @JsonBackReference
    private List<UserProductData> userProductDataList = new ArrayList<>();

    public Product(String name, Type type, Genre genre) {
        this.name = name;
        this.type = type;
        this.genre = genre;
    }

    public void addVideoId(String videoId) {
        this.videoIds.add(videoId);
    }

    public void addUserProductData(UserProductData userProductData) {
        userProductDataList.add(userProductData);
        userProductData.setProduct(this);
    }
}
