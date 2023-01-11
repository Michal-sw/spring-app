package com.projekt.planLekcji.service;

import com.projekt.planLekcji.Person.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Configuration
public class PersonServiceConfig {
    HashMap<String, List<String>> peopleMap = new HashMap<>();

    public PersonServiceConfig () {
        readSourceFile();
    }

    private void readSourceFile() {
        try (Scanner scanner = new Scanner(new File("data.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> fields = Arrays.asList(line.split("\\s*;\\s*"));
                peopleMap.put(fields.get(0), fields);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public Person getPersonFromLine(String line) {
        List<String> fields = Arrays.asList(line.split("\\s*;\\s*"));

        Person person = null;
        if (Objects.equals(fields.get(0), "28")) {
            person = ceo();
        } else if (Objects.equals(fields.get(0), "10")) {
            person = viceCeo();
        } else if (Objects.equals(fields.get(0), "20")) {
            person = secretary();
        }
        if (person == null) return null;

        person.setFirstName(fields.get(1));
        person.setLastName(fields.get(2));
        person.setEmail(fields.get(3));

        return person;
    }
    @Bean
    public Person ceo() {
        List<String> data = peopleMap.get("28");
        if (!data.isEmpty()) {
            Person ceo = new Person();
            ceo.setFirstName(data.get(1));
            ceo.setLastName(data.get(2));
            ceo.setEmail(data.get(3));
            return ceo;
        }
        return null;
    }

    @Bean
    public Person viceCeo() {
        List<String> data = peopleMap.get("10");
        if (!data.isEmpty()) {
            Person viceCeo = new Person();
            viceCeo.setFirstName(data.get(1));
            viceCeo.setLastName(data.get(2));
            viceCeo.setEmail(data.get(3));
            return viceCeo;
        }
        return null;
    }

    @Bean
    public Person secretary() {
        List<String> data = peopleMap.get("20");
        if (!data.isEmpty()) {
            Person secretary = new Person();
            secretary.setFirstName(data.get(1));
            secretary.setLastName(data.get(2));
            secretary.setEmail(data.get(3));
            return secretary;
        }
        return null;
    }

}
