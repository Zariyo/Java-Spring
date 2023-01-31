package pl.edu.ug.lab.wpluzek.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ManufacturerRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ShopRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektApplication.class, args);
	}

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private FurnitureRepository furnitureRepository;

	@PostConstruct
	public void loadDefaults() {
        Manufacturer ikea = new Manufacturer();
        ikea.setName("ikea");
        ikea.setEmail("ikea@reply.com");
        ikea.setAddress("ikea Street");
        manufacturerRepository.save(ikea);
        Furniture sofa = new Furniture();
        sofa.setManufacturer(ikea);
        sofa.setMaterial("Skóra");
        sofa.setName("Sofa skórzana");
        sofa.setPrice(300);
        furnitureRepository.save(sofa);
//        furnitureRepository.save(sofa);
        Shop ikeaShop = new Shop();
        ikeaShop.setAddress("ikea Street");
        ikeaShop.setName("Ikea");
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureList.add(sofa);
        ikeaShop.setAvailableFurniture(furnitureList);

        shopRepository.save(ikeaShop);
        List<Shop> shopList = new ArrayList<>();
        shopList.add(ikeaShop);
        sofa.setSoldAt(shopList);
        furnitureRepository.save(sofa);


	}


}
