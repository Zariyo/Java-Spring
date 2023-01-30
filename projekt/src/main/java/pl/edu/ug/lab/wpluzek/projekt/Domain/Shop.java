package pl.edu.ug.lab.wpluzek.projekt.Domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "shop_furniture",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "furniture_id"))
    private List<Furniture> availableFurniture;


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<Furniture> getAvailableFurniture() {
        return availableFurniture;
    }

    public void setAvailableFurniture(List<Furniture> availableFurniture) {
        this.availableFurniture = availableFurniture;
    }

}
