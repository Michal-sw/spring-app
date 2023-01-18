package com.projekt.planLekcji.service;

import com.projekt.planLekcji.Teacher.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {
    List<Teacher> db = Collections.synchronizedList(new ArrayList<>());

    public Teacher addTeacher(Teacher teacher) {
        if (teacher.getId() == null) {
            teacher.setId(String.valueOf(UUID.randomUUID()));
        }
        db.add(teacher);
        return teacher;
    }

    public Teacher findById(String id) {
        Teacher teacherToFind = null;

        for (Teacher teacher : db) {
            if (teacher.getId().equals(id)) {
                teacherToFind = teacher;
            }
        }

        return teacherToFind;
    }

    public List<Teacher> getAllTeachers() {
        return db;
    }

    public Teacher getTeacher(String id) {
        Teacher teacherToFind = null;
        for (Teacher teacher : db) {
            if (teacher.getId().equals(id)) {
                teacherToFind = teacher;
            }
        }
        return teacherToFind;
    }

    public boolean deleteTeacher(String id) {

        Teacher teacherToDelete = null;
        for (Teacher teacher : db) {
            if (id.equals(teacher.getId())) {
                teacherToDelete = teacher;
            }
        }

        if (teacherToDelete != null) {
            db.remove(teacherToDelete);
            return true;
        }
        return false;
    }


}
