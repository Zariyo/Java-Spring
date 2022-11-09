package pl.edu.ug.lab.wpluzek.lab4.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;
@ImportResource("classpath:persons.xml")

@Configuration
public class PersonServiceConfig {


}
