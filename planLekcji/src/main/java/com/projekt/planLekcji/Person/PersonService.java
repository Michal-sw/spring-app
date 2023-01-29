package com.projekt.planLekcji.Person;
import com.projekt.planLekcji.SchoolGroup.*;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    final PersonRepository personRepository;
    final SchoolGroupRepository schoolGroupRepository;

    public PersonService(PersonRepository personRepository, SchoolGroupRepository schoolGroupRepository) {
        this.personRepository = personRepository;
        this.schoolGroupRepository = schoolGroupRepository;
    }

    private void fillData() {
//        SchoolGroup group1 = new SchoolGroup("1B");
//        SchoolGroup group2 = new SchoolGroup("2B");
//        SchoolGroup group3 = new SchoolGroup("3B");
//
//        schoolGroupRepository.save(group1);
//        schoolGroupRepository.save(group2);
//        schoolGroupRepository.save(group3);
//
//        Person person1 = new Person("Person 1", "Test1", group1);
//        Person person2 = new Person("Person 2", "Test2", group1);
//        Person person3 = new Person("Person 3", "Test3", group1);
//
//        personRepository.save(person1);
//        personRepository.save(person2);
//        personRepository.save(person3);
//
//        group1.addStudent(person1);
//        group1.addStudent(person2);
//        group1.addStudent(person3);
    }

    public Person addPerson(Person personToAdd) {
        personRepository.save(personToAdd);
        return personToAdd;
    }


    public void getAllGroups() {
        fillData();

        // Obserwować liczbę zapytań przy klasycznej i przy własnej implementacji findAll()
        for (SchoolGroup group : schoolGroupRepository.findAll()) {
            System.out.println(group.getStudents());
            System.out.println(group.getName());
        }


    }

    public Person editPerson(Person person) {
        personRepository.save(person);
        return person;
    }

    public Person findById(String id) {
        Optional<Person> person =  personRepository.findById(id);
        return person.orElse(null);
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void deleteById(String id) {
        personRepository.deleteById(id);
    }
}
