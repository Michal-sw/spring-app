package com.projekt.planLekcji.Student;

import com.projekt.planLekcji.SchoolGroup.*;
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
public class StudentWebController {

    private final StudentService studentService;
    private final SchoolGroupService schoolGroupService;

    public StudentWebController(@Autowired StudentService studentService, @Autowired SchoolGroupService schoolGroupService) {
        this.studentService = studentService;
        this.schoolGroupService = schoolGroupService;
    }

    @GetMapping("/student")
    public String students(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("students", studentService.getAllStudents());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "/student/student-all";
    }

    @GetMapping("/student/add")
    public String studentAdd(Model model) {
        model.addAttribute("student", new Student("", "", null));
        model.addAttribute("schoolGroups", schoolGroupService.getAllSchoolGroups());
        return "/student/student-add";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id, Model model) {
        Student student = studentService.findById(id);
        schoolGroupService.deleteStudentFromGroup(student);
        studentService.deleteById(id);
        model.addAttribute("successMessage", "Operacja się powiodła");
        return "redirect:/student";
    }

    @GetMapping("/student/{id}")
    public String studentEditPath(@PathVariable("id") String id, Model model) {
        Student student = studentService.findById(id);
        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("schoolGroups", schoolGroupService.getAllSchoolGroups());
        } else {
            model.addAttribute("student", new Student("", "",null));
        }

        return "/student/student-edit";
    }

    @PostMapping("/student/{id}")
    public String studentEdit(@PathVariable("id") String id, @ModelAttribute Student editedStudent, Model model) {
        Student student = studentService.findById(id);
        if (student != null) {
            studentService.editStudent(editedStudent);
        }

        return "redirect:/student";
    }

    @PostMapping("/student")
    public ModelAndView addNewStudent(@Valid Student student, Errors errors, ModelMap model) {
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.getDefaultMessage());
            }
            model.addAttribute("errorMessage", "Data not valid, try again :)");
        } else {
            studentService.addStudent(student);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/student", model);
    }
}
