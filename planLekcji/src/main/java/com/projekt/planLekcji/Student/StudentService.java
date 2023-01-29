package com.projekt.planLekcji.Student;
import com.projekt.planLekcji.SchoolGroup.*;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    final StudentRepository studentRepository;
    final SchoolGroupRepository schoolGroupRepository;

    public StudentService(StudentRepository studentRepository, SchoolGroupRepository schoolGroupRepository) {
        this.studentRepository = studentRepository;
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
//        Student student1 = new Student("Student 1", "Test1", group1);
//        Student student2 = new Student("Student 2", "Test2", group1);
//        Student student3 = new Student("Student 3", "Test3", group1);
//
//        studentRepository.save(student1);
//        studentRepository.save(student2);
//        studentRepository.save(student3);
//
//        group1.addStudent(student1);
//        group1.addStudent(student2);
//        group1.addStudent(student3);
    }

    public Student addStudent(Student studentToAdd) {
        studentRepository.save(studentToAdd);
        return studentToAdd;
    }


    public void getAllGroups() {
        fillData();

        for (SchoolGroup group : schoolGroupRepository.findAll()) {
            System.out.println(group.getStudents());
            System.out.println(group.getName());
        }


    }

    public Student editStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Student findById(String id) {
        Optional<Student> student =  studentRepository.findById(id);
        return student.orElse(null);
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }
}
