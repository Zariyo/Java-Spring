package pl.edu.ug.lab.wpluzek.projekt.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {


    @GetMapping
    public ModelAndView Home(Model model) {
        return new ModelAndView("Home");

    }
}
