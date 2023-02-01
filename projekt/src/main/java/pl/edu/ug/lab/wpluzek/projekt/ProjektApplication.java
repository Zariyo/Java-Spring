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

        Manufacturer lissy = new Manufacturer();
        lissy.setName("lissy");
        lissy.setEmail("lissy@reply.com");
        lissy.setAddress("lissy Street");
        manufacturerRepository.save(lissy);
        Furniture table = new Furniture();
        table.setManufacturer(lissy);
        table.setMaterial("Drewno");
        table.setName("Stól drewniany");
        table.setPrice(300);
        furnitureRepository.save(table);
        Shop lissyShop = new Shop();
        lissyShop.setAddress("lissy Street");
        lissyShop.setName("lissy");
        List<Furniture> furnitureList1 = new ArrayList<>();
        furnitureList1.add(table);
        lissyShop.setAvailableFurniture(furnitureList1);
        shopRepository.save(lissyShop);
        List<Shop> shopList1 = new ArrayList<>();
        shopList1.add(lissyShop);
        table.setSoldAt(shopList1);
        furnitureRepository.save(table);

        Manufacturer jysk = new Manufacturer();
        jysk.setName("jysk");
        jysk.setEmail("jysk@reply.com");
        jysk.setAddress("jysk Street");
        manufacturerRepository.save(jysk);
        Furniture kanapa = new Furniture();
        kanapa.setManufacturer(ikea);
        kanapa.setMaterial("Skóra");
        kanapa.setName("kanapa skórzana");
        kanapa.setPrice(300);
        furnitureRepository.save(kanapa);
        Shop jyskShop = new Shop();
        jyskShop.setAddress("jysk Street");
        jyskShop.setName("jysk");
        List<Furniture> furnitureList2 = new ArrayList<>();
        furnitureList2.add(kanapa);
        jyskShop.setAvailableFurniture(furnitureList2);
        shopRepository.save(jyskShop);
        List<Shop> shopList2 = new ArrayList<>();
        shopList2.add(jyskShop);
        kanapa.setSoldAt(shopList2);
        furnitureRepository.save(kanapa);

            Manufacturer abra = new Manufacturer();
            abra.setName("abra");
            abra.setEmail("abra@reply.com");
            abra.setAddress("abra Street");
            manufacturerRepository.save(abra);
            Furniture chair = new Furniture();
            chair.setManufacturer(abra);
            chair.setMaterial("steel");
            chair.setName("Metalowe krzesło");
            chair.setPrice(120);
            furnitureRepository.save(chair);
            Shop abraShop = new Shop();
            abraShop.setAddress("abra Street");
            abraShop.setName("abra");
            List<Furniture> furnitureList3 = new ArrayList<>();
            furnitureList3.add(chair);
            abraShop.setAvailableFurniture(furnitureList3);
            shopRepository.save(abraShop);
            List<Shop> shopList3 = new ArrayList<>();
            shopList3.add(abraShop);
            chair.setSoldAt(shopList3);
            furnitureRepository.save(chair);


	}


}
