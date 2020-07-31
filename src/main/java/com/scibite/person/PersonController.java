package com.scibite.person;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/person")
    public void newPerson(@RequestBody Person newPerson) {
        Person person = getByFullName(newPerson.getFirstName(), newPerson.getLastName());
        if (person != null) {
            return; // Don't add a new person with the same name
        }
        repository.save(newPerson);
    }

    @GetMapping("/person/all")
    List<Person> returnAll() {
        return repository.findAll();
    }

    @GetMapping("/person")
    Person getByFullName(@RequestParam(value = "first_name") String firstName,
                         @RequestParam(value = "last_name") String lastName) {
        List<Person> peopleWithLastName = repository.findByLastName(lastName);
        Optional<Person> optionalPerson = peopleWithLastName.stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .findFirst();

        return optionalPerson.orElse(null);
    }

    @GetMapping("/person/{id}")
    Person getById(@PathVariable String id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    @PutMapping("/person/{id}")
    void updateById(@PathVariable String id,
                    @RequestBody Person updatedPerson) {
        if (!repository.findById(id).isPresent()) {
            return;
        }

        Person person = repository.findById(id).get();
        person.updateDetails(updatedPerson);

        repository.save(person);
    }

    @DeleteMapping("/person/{id}")
    void deleteById(@PathVariable String id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/person/all")
    void deleteAll() {
        repository.deleteAll();
    }
}