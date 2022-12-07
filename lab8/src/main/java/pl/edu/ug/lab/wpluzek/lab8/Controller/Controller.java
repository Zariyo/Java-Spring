package pl.edu.ug.lab.wpluzek.lab8.Controller;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import pl.edu.ug.lab.wpluzek.lab8.Domain.Person;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String home(Model model) throws ParseException {
        //Person a = new Person("A", 12, "44-444", 1000, true)
        model.addAttribute("person", new Person());
        System.out.println("test");
        return "home";
    }
    @PostMapping("/create")
    public String processOrder(@Valid Person person, Errors errors){
    if(errors.hasErrors()){
        return "home";
    }
    return "redirect:/";
    }
}
