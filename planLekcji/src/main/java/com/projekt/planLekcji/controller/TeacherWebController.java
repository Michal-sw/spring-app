package com.projekt.planLekcji.controller;

import com.projekt.planLekcji.Teacher.Teacher;
import com.projekt.planLekcji.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TeacherWebController {

    private final TeacherServiceImpl teacherService;
    public TeacherWebController(@Autowired TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
        Teacher testTeacher = new Teacher("Olo", "Maciak", "Physics");
        Teacher testTeacher2 = new Teacher("User", "Testowy", "Math");

        teacherService.addTeacher(testTeacher);
        teacherService.addTeacher(testTeacher2);
    }

    @GetMapping("/teacher")
    public String teachers(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("allTeachersFromDB", teacherService.getAllTeachers());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "teacher-all";
    }

    @GetMapping("/teacher/add")
    public String teacherAdd(Model model) {
        model.addAttribute("teacher", new Teacher("", "", ""));
        return "teacher-add";
    }

    @GetMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable("id") String id, Model model) {
        if (teacherService.deleteTeacher(id)) {
            model.addAttribute("successMessage", "Operacja się powiodła");
        }
        else {
            model.addAttribute("errorMessage", "Operacja się nie powiodła");
        }
        return "redirect:/teacher";
    }

    @GetMapping("/teacher/{id}")
    public String teacherEditPath(@PathVariable("id") String id, Model model) {
        Teacher teacher = teacherService.getTeacher(id);
        if (teacher != null) {
            model.addAttribute("teacher", teacher);
        } else {
            model.addAttribute("teacher", new Teacher("", "", ""));
        }

        return "teacher-edit";
    }

    @PostMapping("/teacher/{id}")
    public String teacherEdit(@PathVariable("id") String id, @ModelAttribute Teacher editedTeacher, Model model) {
        Teacher teacher = teacherService.getTeacher(id);
        if (teacher != null) {
            teacher.setFirstName(editedTeacher.getFirstName());
            teacher.setLastName(editedTeacher.getLastName());
        }

        return "redirect:/teacher";
    }

    @PostMapping("/teacher")
    public ModelAndView addNewTeacher(@Valid Teacher teacher, Errors errors, ModelMap model) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.toString());
            }
            model.addAttribute("errorMessage", "Data not valid, try again :)");
        } else {
            teacherService.addTeacher(teacher);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/teacher", model);
    }



}
