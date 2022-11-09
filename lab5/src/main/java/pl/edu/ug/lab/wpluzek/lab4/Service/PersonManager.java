package pl.edu.ug.lab.wpluzek.lab4.Service;

import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;

import java.util.List;

public interface PersonManager {

    List<Person> getAllPersons();
    Person addPerson(Person person);

    boolean deletePerson(String id);

}


