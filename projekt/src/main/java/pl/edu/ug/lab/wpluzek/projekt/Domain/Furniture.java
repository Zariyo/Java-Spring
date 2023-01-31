package pl.edu.ug.lab.wpluzek.projekt.Domain;

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

    @OneToOne
    private Manufacturer manufacturer;
    private String name;
    private String material;
    private double price;

    @ManyToMany(mappedBy = "availableFurniture", fetch = FetchType.EAGER)
    private List<Shop> soldAt;


    public List<Shop> getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(List<Shop> soldAt) {
        this.soldAt = soldAt;
    }
}
