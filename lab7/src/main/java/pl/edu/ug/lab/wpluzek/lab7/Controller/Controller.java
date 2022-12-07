package pl.edu.ug.lab.wpluzek.lab7.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.ug.lab.wpluzek.lab7.Model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Controller
public class Controller {

    public String getDate(Date oldDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = oldDate;
        Date secondDate = new Date();

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return String.valueOf(diff);
    }

    @GetMapping("/")
    public String home(Model model) throws ParseException {
        User user = new User("1",15, "Jan",new Date("02/02/2022"), "man");
        String datediff = getDate(user.getRegistrationDate());
        model.addAttribute("datediff", datediff);
        model.addAttribute("user", user);
        return "home";
    }
}
