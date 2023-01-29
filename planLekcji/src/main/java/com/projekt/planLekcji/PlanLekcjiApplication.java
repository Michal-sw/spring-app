package com.projekt.planLekcji;

import com.projekt.planLekcji.Person.PersonRepository;
import com.projekt.planLekcji.Person.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PlanLekcjiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanLekcjiApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(PersonService personService) {
		return (args) -> {
			personService.getAllGroups();


		};

	}
}
