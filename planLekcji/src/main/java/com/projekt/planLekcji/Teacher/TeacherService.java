package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.Lesson.Lesson;
import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Teacher.Teacher;
import com.projekt.planLekcji.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    public Teacher findByIdWithAllLeasons(String id) {
        Optional<List<Teacher>> teachers =  teacherRepository.findByIdWithLessons(id);
        if (teachers.isPresent()) {
            return teachers.get().get(0);
        }
        return null;
    }

    public Iterable<Speciality> getAllLessonSubjects() {
        return teacherRepository.findAllTeacherSubjects();
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void deleteById(String id) {
        teacherRepository.deleteById(id);
    }
}
