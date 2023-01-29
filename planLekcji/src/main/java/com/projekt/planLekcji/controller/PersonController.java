package com.projekt.planLekcji.controller;

import com.projekt.planLekcji.Person.Person;
import com.projekt.planLekcji.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(@Autowired PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/api/person")
    ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person createdPerson = personService.addPerson(person);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("X-Custem-Header", "123");


        return new ResponseEntity<>(createdPerson, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/api/person")
    Person modifyPerson(@RequestBody Person person) {
        Person foundPerson = personService.findById(person.getId());

        foundPerson.setFirstName(person.getFirstName());
        foundPerson.setLastName(person.getLastName());

        return person;
    }

    @GetMapping("/api/person")
    Iterable<Person> getAll() {
        return personService.getAllPersons();
    }

    @GetMapping("/api/person/{id}")
    Person findPerson(@PathVariable("id") String id) {
        Person foundPerson = personService.findById(id);
        if (foundPerson == null) {
            throw new PersonNotFoundException();
        }
        return foundPerson;
    }

}
