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
import pl.edu.ug.lab.wpluzek.projekt.Domain.ProductCard;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ProductCardRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productCard")
public class ProductCardController {

    @Autowired
    private ProductCardRepository productCardRepository;
    // Assuming you have the repository for the related furniture as well.
    @Autowired
    private FurnitureRepository furnitureRepository;

    @GetMapping("/add/{relatedFurnitureId}")
    public ModelAndView showAddProductCardForm(@PathVariable Long relatedFurnitureId, Model model) {
        Optional<Furniture> relatedFurniture = furnitureRepository.findById(relatedFurnitureId);
        relatedFurniture.ifPresent(furniture -> model.addAttribute("relatedFurniture", furniture));
        model.addAttribute("productCard", new ProductCard());
        return new ModelAndView("productCard/AddProductCard");
    }

    @PostMapping("/add/{relatedFurnitureId}")
    public RedirectView addProductCard(@PathVariable Long relatedFurnitureId, @ModelAttribute ProductCard productCard) {
        Furniture relatedFurniture = furnitureRepository.findById(relatedFurnitureId).orElse(null);
        productCard.setRelatedFurniture(relatedFurniture);
        productCardRepository.save(productCard);
        return new RedirectView("/productCard");
    }



    @GetMapping("/{id}")
    public ModelAndView getProductCardDetails(@PathVariable Long id, Model model) {
        ProductCard productCard = productCardRepository.findById(id).orElse(null);
        model.addAttribute("productCard", productCard);
        return new ModelAndView("productCard/ProductCardDetails");
    }

    @PutMapping("/{id}")
    public ProductCard updateProductCard(@PathVariable Long id, @Validated @RequestBody ProductCard productCardRequest) {
        return productCardRepository.findById(id)
                .map(productCard -> {
                    productCard.setModelNumber(productCardRequest.getModelNumber());
                    productCard.setMaterialsUsed(productCardRequest.getMaterialsUsed());
                    productCard.setWarrantyPeriod(productCardRequest.getWarrantyPeriod());
                    return productCardRepository.save(productCard);
                }).orElseThrow(() -> new ResourceNotFoundException("Product Card not found with id " + id));
    }
}

