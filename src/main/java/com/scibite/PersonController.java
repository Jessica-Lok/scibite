package com.scibite;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/person")
    public Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/person/")
    List<Person> returnAll() {
        return repository.findAll();
    }

    @GetMapping("/person")
    Person getByFirstName(@RequestParam(value = "first_name") String firstName) {
        return repository.findByFirstName(firstName);
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

    @DeleteMapping("/person")
    void deleteAll() {
        repository.deleteAll();
    }
}