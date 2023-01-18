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

import java.util.List;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @GetMapping
    public Iterable<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    @GetMapping("/manufacturer/{manufacturerName}")
    public ModelAndView getFurnitureByManufacturer(@PathVariable String manufacturerName, Model model) {
        List<Furniture> furnitureList = furnitureRepository.findByManufacturerName(manufacturerName);
        model.addAttribute("furnitureList", furnitureList);
        return new ModelAndView("GetFurnitureByManufacturer");
    }


    @GetMapping("/{id}")
    public Furniture getFurnitureById(@PathVariable Long id) {
        return furnitureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Furniture not found with id " + id));
    }

    @PostMapping
    public Furniture createFurniture(@Validated @RequestBody Furniture furniture) {
        return furnitureRepository.save(furniture);
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
