package com.projekt.planLekcji.service;

import com.projekt.planLekcji.Person.Person;

import java.util.List;

public interface PersonService {

    Person addPerson(Person person);
    List<Person> getAllPersons();
    boolean deletePerson(String id);
    Person findById(String id);

    Person getPerson(String id);
}

