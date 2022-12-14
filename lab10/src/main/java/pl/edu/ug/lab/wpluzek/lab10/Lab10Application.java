package pl.edu.ug.lab.wpluzek.lab10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.ug.lab.wpluzek.lab10.Service.CustomerService;

@SpringBootApplication
public class Lab10Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab10Application.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(@Autowired CustomerService customerService) {
		return (args) -> {

			customerService.setCustomers();

		};
	}


}
