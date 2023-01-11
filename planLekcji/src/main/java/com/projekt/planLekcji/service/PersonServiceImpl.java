package com.projekt.planLekcji.service;

import com.projekt.planLekcji.Person.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    PersonServiceConfig personServiceConfig = new PersonServiceConfig();
    List<Person> db = Collections.synchronizedList(new ArrayList<>());

    private Person director;

    public Person addPerson(Person person) {
        if (person.getId() == null) {
            person.setId(String.valueOf(UUID.randomUUID()));
        }
        db.add(person);
        return person;
    }

    public Person findById(String id) {
        Person personToFind = null;

        for (Person person : db) {
            if (person.getId().equals(id)) {
                personToFind = person;
            }
        }

        return personToFind;
    }

    public List<Person> getAllPersons() {
        return db;
    }

    public Person getPerson(String id) {
        Person personToFind = null;
        for (Person person : db) {
            if (person.getId().equals(id)) {
                personToFind = person;
            }
        }
        return personToFind;
    }

    public boolean deletePerson(String id) {

        Person personToDelete = null;
        for (Person person : db) {
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
