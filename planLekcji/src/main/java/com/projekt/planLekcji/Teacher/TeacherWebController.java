package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Student.StudentService;
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

    private final StudentService studentService;
    private final TeacherService teacherService;

    public TeacherWebController(@Autowired StudentService studentService, @Autowired TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping("/teacher")
    public String teacher(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "/teacher/teacher-all";
    }

    @GetMapping("/teacher/add")
    public String teacherAdd(Model model) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("specialities", Speciality.values());

        return "/teacher/teacher-add";
    }

    @GetMapping("/teacher/delete/{id}")
    public ModelAndView deleteTeacher(@PathVariable("id") String id, ModelMap model) {
        Teacher teacherOptional = teacherService.findByIdWithAllLeasons(id);
        if (teacherOptional != null && teacherOptional.getLessons().size() > 0) {
            model.addAttribute("errorMessage", "Remove teacher from all lessons first");
            return new ModelAndView("redirect:/teacher", model);
        }
        teacherService.deleteById(id);
        model.addAttribute("successMessage", "Operacja się powiodła");

        return new ModelAndView("redirect:/teacher", model);
    }

    @GetMapping("/teacher/{id}")
    public String teacherEditPath(@PathVariable("id") String id, Model model) {
        Teacher teacher = teacherService.findById(id);

        if (teacher != null) {
            model.addAttribute("teacher", teacher);
        } else {
            model.addAttribute("teacher", new Teacher());
        }
        model.addAttribute("specialities", Speciality.values());

        return "/teacher/teacher-edit";
    }

    @PostMapping("/teacher/{id}")
    public String teacherEdit(@PathVariable("id") String id, @ModelAttribute Teacher editedTeacher, Model model) {
        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            teacherService.editTeacher(editedTeacher);
        }

        return "redirect:/teacher";
    }

    @PostMapping("/teacher")
    public ModelAndView addNewTeacher(@Valid Teacher teacher, Errors errors, ModelMap model) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.getDefaultMessage());
            }
        } else {
            teacherService.addTeacher(teacher);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/teacher", model);
    }
}
