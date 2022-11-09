package pl.edu.ug.lab.wpluzek.lab4.Service;

import org.springframework.stereotype.Component;
import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PersonManagerInMemory implements PersonManager {

    private final List<Person> db = new ArrayList<>();

    @Override
    public List<Person> getAllPersons() {
        return db;
    }

    public Person getPerson(String id){
        Person toReturn = null;
        for (Person person: db) {
            if (id.equals(person.getId())) {
                toReturn = person;
            }
            break;
        }
        return toReturn;
    }

    @Override
    public Person addPerson(Person person) {
        Person newPerson = new Person(UUID.randomUUID().toString(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getCompanyName());
        db.add(newPerson);
        return newPerson;
    }

    public Person editPerson(String id, Person newPerson){
        Person personToEdit = null;
        for (Person person: db) {
            if (id.equals(person.getId())) {
                person.setCompanyName(newPerson.getCompanyName());
                person.setEmail(newPerson.getEmail());
                person.setFirstName(newPerson.getFirstName());
                person.setLastName(newPerson.getLastName());
                personToEdit = person;
                break;
            }
        }
        return personToEdit;
    }

    @Override
    public boolean deletePerson(String id) {

        Person personToDelete = null;
        for (Person person: db) {
            if (id.equals(person.getId())) {
                personToDelete = person;
            }
        }

        if (personToDelete != null) {
            db.remove(personToDelete);
            return true;
        }

        return false;
    }
}

