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



    @Bean
    @Qualifier("panJan")
    @Scope("singleton")
    public Person createMan(){
        return new Person("Jan", "Janek", "jan@jan.jan", "Janpol");
    }

//    @Bean
//    public Person prezes() {
//        return new Person("Chrystal", "Havoc", "chavocr@yahoo.com", "mymm");
//    }
//
//    @Bean
//    public Person wiceprezes() {
//        return new Person("Halley", "Gadaud", "hgadaud9@sohu.com", "Oyope");
//    }
//
//    @Bean
//    public Person sekretarz(){
//        return new Person("Kirbie", "Wrettum", "kwrettumj@slideshare.net", "Browsetype");
//    }
}
