package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ShopRepository;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private FurnitureRepository furnitureRepository;

    @GetMapping
    public ModelAndView getAllShops(Model model) {
        List<Shop> shopsList = (List<Shop>) shopRepository.findAll();
        model.addAttribute("shopsList", shopsList);
        return new ModelAndView("shop/GetShopList");
    }

    @GetMapping("/{id}")
    @Transactional
    public ModelAndView getShopDetails(@PathVariable Long id, Model model) {
        Shop shop = shopRepository.findById(id).orElse(null);
        model.addAttribute("shop", shop);
        return new ModelAndView("shop/ShopDetails");
    }

    @GetMapping("/add")
    public ModelAndView showAddShopForm(Model model) {
        model.addAttribute("shop", new Shop());
        return new ModelAndView("shop/AddShop");
    }

    @PostMapping("/add")
    public String addShop(@ModelAttribute Shop shop) {
        shopRepository.save(shop);
        return "shop_added";
    }

    @PutMapping("/addFurniture")
    public Shop addFurniture(@RequestBody Shop shop, @RequestParam Long furnitureId) {
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        shop.getAvailableFurniture().add(furniture);
        return shopRepository.save(shop);
    }

    @PutMapping("/removeFurniture")
    public Shop removeFurniture(@RequestBody Shop shop, @RequestParam Long furnitureId) {
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        shop.getAvailableFurniture().remove(furniture);
        return shopRepository.save(shop);
    }


}
