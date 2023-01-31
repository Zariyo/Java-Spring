package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ManufacturerRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ShopRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {


    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping
    public ModelAndView Home(Model model) {
        return new ModelAndView("Home");

    }

    @GetMapping("/loadDefaults")
    public RedirectView loadDefaults(Model model) {
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
        return new RedirectView("/");

    }
}
