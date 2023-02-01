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
@RequestMapping("/furniture/{furnitureId}/productCard")
public class ProductCardController {

    @Autowired
    private ProductCardRepository productCardRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;

    @GetMapping("/add")
    public ModelAndView showAddProductCardForm(@PathVariable Long furnitureId, Model model) {
        Optional<Furniture> relatedFurniture = furnitureRepository.findById(furnitureId);
        relatedFurniture.ifPresent(furniture -> model.addAttribute("relatedFurniture", furniture));
        model.addAttribute("productCard", new ProductCard());
        return new ModelAndView("productCard/AddProductCard");
    }

    @PostMapping("/add")
    public ModelAndView addProductCard(@PathVariable Long furnitureId, @ModelAttribute ProductCard productCard, Model model) {
        Furniture relatedFurniture = furnitureRepository.findById(furnitureId).orElse(null);
        if(relatedFurniture == null){
            throw new ResourceNotFoundException("No furniture found with id " + furnitureId);
        }
        productCard.setRelatedFurniture(relatedFurniture);
        productCardRepository.save(productCard);
        relatedFurniture.setProductCard(productCard);
        furnitureRepository.save(relatedFurniture);
        model.addAttribute("productCard", productCard);
        return new ModelAndView("productCard/ProductCardDetails");
    }

    @GetMapping
    public ModelAndView getProductCard(@PathVariable Long furnitureId, Model model) {
        Furniture relatedFurniture = furnitureRepository.findById(furnitureId).orElse(null);
        if(relatedFurniture == null){
            throw new ResourceNotFoundException("No furniture found with id " + furnitureId);
        }
        ProductCard productCard = relatedFurniture.getProductCard();
        model.addAttribute("productCard", productCard);
        return new ModelAndView("productCard/ProductCardDetails");
    }



    @PutMapping
    public ProductCard updateProductCard(@PathVariable Long furnitureId, @Validated @RequestBody ProductCard productCardRequest) {
        Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
        if (furniture == null) {
            throw new ResourceNotFoundException("Furniture not found with id " + furnitureId);
        }
        long productCardId = furniture.getProductCard().getId();
        ProductCard productCard = productCardRepository.findById(productCardId);
        productCard.setModelNumber(productCardRequest.getModelNumber());
        productCard.setMaterialsUsed(productCardRequest.getMaterialsUsed());
        productCard.setWarrantyPeriod(productCardRequest.getWarrantyPeriod());
        return productCardRepository.save(productCard);
    }
}

