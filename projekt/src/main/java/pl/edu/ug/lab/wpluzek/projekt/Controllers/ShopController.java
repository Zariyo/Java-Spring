package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Furniture;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Manufacturer;
import pl.edu.ug.lab.wpluzek.projekt.Domain.Shop;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.FurnitureRepository;
import pl.edu.ug.lab.wpluzek.projekt.Repositories.ShopRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    public ModelAndView addShop(@ModelAttribute Shop shop) {
        shopRepository.save(shop);
        return new ModelAndView("shop/GetShopList");
    }

    @GetMapping("/{id}/addFurniture")
    public ModelAndView addFurnitureForm(Model model, @PathVariable Long id){
        Shop shop = shopRepository.findById(id).orElse(null);
        List<Furniture> furnitures = (List<Furniture>) shopRepository.findNewFurniture(id);
        model.addAttribute(furnitures);
        if(shop == null){
            throw new ResourceNotFoundException("Shop not found with id: " + id);
        }
        model.addAttribute(shop);
        return new ModelAndView("shop/AddFurniture");
    }

    @PostMapping("/{shopId}/addFurniture")
    public ModelAndView addFurniture(@PathVariable long shopId, @RequestParam List<Long> furnitureIds, Model model) {
        Shop shop = shopRepository.findById(shopId);

        for (Long furnitureId : furnitureIds) {
            Furniture furniture = furnitureRepository.findById(furnitureId).orElse(null);
            shop.getAvailableFurniture().add(furniture);
        }
        shopRepository.save(shop);
        model.addAttribute(shop);
        return new ModelAndView("shop/ShopDetails");
    }

    @GetMapping("/{id}/sendEmail")
    public RedirectView sendEmail(@PathVariable long id, Model model, @RequestParam("email") String email) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("wpluzek.uglab.test@gmail.com");
        mailSender.setPassword("sabcpkdxrebowjok");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        Shop shop = shopRepository.findById(id);
        if (shop == null) {
            return new RedirectView("/shop/" + id);
        }

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Shop Name: " + shop.getName() + "\n");
        emailBody.append("\nAvailable Furniture:\n");
        for (Furniture furniture : shop.getAvailableFurniture()) {
            emailBody.append("Name: " + furniture.getName() + "\n");
            emailBody.append("Material: " + furniture.getMaterial() + "\n");
            emailBody.append("Price: " + furniture.getPrice() + "\n");
            emailBody.append("Manufacturer: " + furniture.getManufacturer().getName() + "\n");
            emailBody.append("\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wpluzek.uglab.test@gmail.com");
        message.setTo(email);
        message.setSubject("Shop Details");
        message.setText(emailBody.toString());

        mailSender.send(message);

        return new RedirectView("/shop/" + id);
    }


    @PutMapping("/removeFurniture")
    public Shop removeFurniture(@RequestBody Shop shop, @RequestParam Long furnitureId) {
        Furniture furniture = furnitureRepository.findById(furnitureId).get();
        shop.getAvailableFurniture().remove(furniture);
        return shopRepository.save(shop);
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteShop(@PathVariable Long id) {
        shopRepository.deleteById(id);
        return new ModelAndView("shop/GetShopList");
    }

    @GetMapping("/{shopId}/edit")
    public ModelAndView getShopEditForm(@PathVariable Long shopId, Model model) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        model.addAttribute("shop", shop);
        return new ModelAndView("shop/EditShop");
    }

    @PutMapping("/{shopId}/edit")
    public ModelAndView updateShop(@PathVariable Long shopId, @Validated @RequestBody Shop shopRequest) {
        Shop shop = shopRepository.findById(shopId).orElse(null);
        if (shop == null) {
            throw new ResourceNotFoundException("Shop not found with id " + shopId);
        }
        shop.setName(shopRequest.getName());
        shop.setAddress(shopRequest.getAddress());
        shopRepository.save(shop);
        return new ModelAndView("/shop/GetShopList");
    }

    @GetMapping("/{id}/saveFurniture")
    public ResponseEntity<String> saveFurniture(@PathVariable("id") Long id) {
        try {
            List<Furniture> availableFurniture = getAvailableFurniture(id);
            writeToFile(availableFurniture, "availalbeFurniture.txt");
            return new ResponseEntity<>("Furniture saved successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error saving furniture: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Furniture> getAvailableFurniture(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if (shop == null){
            throw new ResourceNotFoundException("Shop not found");
        }
        return shop.getAvailableFurniture();
    }

    private void writeToFile(List<Furniture> availableFurniture, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Furniture furniture : availableFurniture) {
                fileWriter.write("Name: " + furniture.getName() + '\n');
                fileWriter.write("Material: " + furniture.getMaterial() + '\n');
                fileWriter.write("Manufacturer: " + furniture.getManufacturer().getName() + '\n');
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
