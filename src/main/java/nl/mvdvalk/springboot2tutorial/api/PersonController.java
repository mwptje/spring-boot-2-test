package nl.mvdvalk.springboot2tutorial.api;

import nl.mvdvalk.springboot2tutorial.model.Person;
import nl.mvdvalk.springboot2tutorial.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    Set base request mapping and set the class to a request controller for the rest api
 */
@RequestMapping("/api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Post request and the contents come from the request body in JSON format
    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

}
