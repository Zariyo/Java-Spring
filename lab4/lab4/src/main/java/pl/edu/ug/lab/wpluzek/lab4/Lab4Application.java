package pl.edu.ug.lab.wpluzek.lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;
import pl.edu.ug.lab.wpluzek.lab4.Service.PersonService;
import pl.edu.ug.lab.wpluzek.lab4.Service.PersonServiceConfig;

import java.beans.Beans;

@SpringBootApplication
public class Lab4Application {



	public static void main(String[] args) {

		ApplicationContext applicationContext
				= SpringApplication.run(Lab4Application.class, args);
		System.out.println(applicationContext.getBeansOfType(Person.class));

		PersonService personService = applicationContext.getBean(PersonService.class);
		System.out.println(personService.getDirector().toString());


//
//		System.out.println(context.getBean("prezes"));
//		Person prezes = context.getBean("prezes", Person.class);
//		System.out.println(prezes.toString());
//		System.out.println(wiceprezes.toString());
//		System.out.println(sekretarz.toString());
	}

}
