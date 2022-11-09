package pl.edu.ug.lab.wpluzek.lab4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;

@Service
public class PersonService {

    private final Person director;

    public PersonService(@Autowired @Qualifier("panJan") Person director) {
        this.director = director;
    }

    public Person getDirector() {
        return this.director;
    }


//        public Person prezes() {
//        return new Person("Chrystal", "Havoc", "chavocr@yahoo.com", "mymm");
//    }

//
//      public Map<String, Person> prezes = context.getBeansOfType(Person.class);
//    Person wiceprezes = context.getBean("wiceprezes", Person.class);
//    Person sekretarz = context.getBean("sekretarz", Person.class);
}
