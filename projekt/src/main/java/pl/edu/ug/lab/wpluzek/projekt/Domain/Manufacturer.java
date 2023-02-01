package pl.edu.ug.lab.wpluzek.projekt.Domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Furniture> producedFurniture;
    private String name;
    private String address;

    public List<Furniture> getProducedFurniture() {
        return producedFurniture;
    }

    private String email;
}

