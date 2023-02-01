package pl.edu.ug.lab.wpluzek.projekt.Domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_card_id")
    private ProductCard productCard;


    @NotNull
    private String name;

    @NotNull
    private String material;

    @NotNull
    private double price;

    private String imageUrl;

    public ProductCard getProductCard() {
        return productCard;
    }

    public void setProductCard(ProductCard productCard) {
        this.productCard = productCard;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToMany(mappedBy = "availableFurniture", fetch = FetchType.EAGER)
    private List<Shop> soldAt;


    public List<Shop> getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(List<Shop> soldAt) {
        this.soldAt = soldAt;
    }
}
