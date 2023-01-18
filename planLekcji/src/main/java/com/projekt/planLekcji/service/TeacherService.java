package com.projekt.planLekcji.service;

import com.projekt.planLekcji.Teacher.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    boolean deleteTeacher(String id);
    Teacher findById(String id);

    Teacher getTeacher(String id);
}

