package pl.edu.ug.lab.wpluzek.lab4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.edu.ug.lab.wpluzek.lab4.Domain.Person;
import pl.edu.ug.lab.wpluzek.lab4.Service.PersonManagerInMemory;

import java.util.List;

@RestController
public class PersonController {

    public PersonController(@Autowired PersonManagerInMemory personManager) {
        this.personManager = personManager;
    }

    private final PersonManagerInMemory personManager;

    @PostMapping("/api/person")
    public Person addPerson(@RequestBody Person person){
        return personManager.addPerson(person);
    }

    @PutMapping("/api/person/{id}")
    public Person editPerson(@PathVariable("id") String id, @RequestBody Person person){
        return personManager.editPerson(id, person);
    }

    @GetMapping("/api/person/{id}")
    public Person getPerson(@PathVariable("id") String id){
        return personManager.getPerson(id);
    }

    @GetMapping("/api/person")
    public List<Person> getAllPersons(){
        return personManager.getAllPersons();
    }

    @DeleteMapping("/api/person/{id}")
    public boolean deletePerson(@PathVariable("id") String id){
        return personManager.deletePerson(id);
    }

    @GetMapping("/api/hello")
    public String helloWorld(){
        return "Hello World!";
    }

}
