package com.projekt.planLekcji;

import com.projekt.planLekcji.Student.StudentRepository;
import com.projekt.planLekcji.Student.StudentService;
import com.projekt.planLekcji.SchoolGroup.SchoolGroupService;
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
	public CommandLineRunner setUpApp(
			StudentService studentService,
			SchoolGroupService schoolGroupService
	) {
		return (args) -> {
			studentService.getAllGroups();


		};

	}
}
