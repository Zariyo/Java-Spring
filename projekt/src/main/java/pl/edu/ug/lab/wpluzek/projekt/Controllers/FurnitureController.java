package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ManufacturerRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping
    public ModelAndView getAllFurniture(Model model) {
        List<Furniture> furnitureList = (List<Furniture>) furnitureRepository.findAll();
        model.addAttribute("furnitureList", furnitureList);
        return new ModelAndView("furniture/GetFurnitureList");
    }

    @GetMapping("/add")
    public ModelAndView showAddFurnitureForm(Model model) {
        model.addAttribute("furniture", new Furniture());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return new ModelAndView("furniture/AddFurniture");
    }

    @PostMapping("/add")
    public ModelAndView addFurniture(@ModelAttribute Furniture furniture) {
        furnitureRepository.save(furniture);
        return new ModelAndView("furniture/GetFurnitureList");
    }

    @GetMapping("/{id}")
    public ModelAndView getFurnitureDetails(@PathVariable Long id, Model model) {
        Furniture furniture = furnitureRepository.findById(id).orElse(null);
        model.addAttribute("furniture", furniture);
        return new ModelAndView("furniture/FurnitureDetails");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getFurnitureEditForm(@PathVariable Long id, Model model) {
        Furniture furniture = furnitureRepository.findById(id).orElse(null);
        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerRepository.findAll();
        model.addAttribute("furniture", furniture);
        model.addAttribute("manufacturers", manufacturers);
        return new ModelAndView("furniture/EditFurniture");
    }

    @PutMapping("/{id}")
    public ModelAndView updateFurniture(@PathVariable Long id, @RequestBody Map<String, Object> data) {

        String name = (String) data.get("name");
        String material = (String) data.get("material");
        double price = Double.parseDouble(data.get("price").toString());
        Long manufacturerId = Long.valueOf(data.get("manufacturerId").toString());

        Furniture furniture = furnitureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Furniture not found"));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        furniture.setName(name);
        furniture.setMaterial(material);
        furniture.setPrice(price);
        furniture.setManufacturer(manufacturer);
        furnitureRepository.save(furniture);

        return new ModelAndView("furniture/GetFurnitureList");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFurniture(@PathVariable Long id) {
        return furnitureRepository.findById(id)
                .map(furniture -> {
                    furnitureRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Furniture not found with id " + id));
    }
}
