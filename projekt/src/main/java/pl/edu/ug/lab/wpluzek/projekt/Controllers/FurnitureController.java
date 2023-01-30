package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ManufacturerRepository;

import java.util.List;

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
    public String addFurniture(@ModelAttribute Furniture furniture) {
        furnitureRepository.save(furniture);
        return "furniture_added";
    }

    @GetMapping("/{id}")
    public ModelAndView getFurnitureDetails(@PathVariable Long id, Model model) {
        Furniture furniture = furnitureRepository.findById(id).orElse(null);
        model.addAttribute("furniture", furniture);
        return new ModelAndView("furniture/FurnitureDetails");
    }

    @PutMapping("/{id}")
    public Furniture updateFurniture(@PathVariable Long id, @Validated @RequestBody Furniture furnitureRequest) {
        return furnitureRepository.findById(id)
                .map(furniture -> {
                    furniture.setManufacturer(furnitureRequest.getManufacturer());
                    furniture.setName(furnitureRequest.getName());
                    furniture.setMaterial(furnitureRequest.getMaterial());
                    furniture.setPrice(furnitureRequest.getPrice());
                    return furnitureRepository.save(furniture);
                }).orElseThrow(() -> new ResourceNotFoundException("Furniture not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFurniture(@PathVariable Long id) {
        return furnitureRepository.findById(id)
                .map(furniture -> {
                    furnitureRepository.delete(furniture);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Furniture not found with id " + id));
    }
}
