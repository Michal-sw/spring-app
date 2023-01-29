package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.Teacher.Teacher;
import com.projekt.planLekcji.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {

    final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher addTeacher(Teacher teacherToAdd) {
        teacherRepository.save(teacherToAdd);
        return teacherToAdd;
    }

    public Teacher editTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public Teacher findById(String id) {
        Optional<Teacher> teacher =  teacherRepository.findById(id);
        return teacher.orElse(null);
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void deleteById(String id) {
        teacherRepository.deleteById(id);
    }
}
