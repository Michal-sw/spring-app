package com.projekt.planLekcji.Student;

import com.projekt.planLekcji.controller.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/api/student")
    ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);

        HttpHeaders httpHeaders = new HttpHeaders();

        return new ResponseEntity<>(createdStudent, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/api/student")
    Student modifyStudent(@RequestBody Student student) {
        studentService.editStudent(student);
        return student;
    }

    @GetMapping("/api/student")
    Iterable<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/api/student/{id}")
    Student findStudent(@PathVariable("id") String id) {
        Student foundStudent = studentService.findById(id);
        if (foundStudent == null) {
            throw new StudentNotFoundException();
        }
        return foundStudent;
    }

}
