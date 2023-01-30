package com.projekt.planLekcji.SchoolGroup;

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
public class SchoolGroupWebController {

    private final StudentService studentService;
    private final SchoolGroupService schoolGroupService;

    public SchoolGroupWebController(@Autowired StudentService studentService, @Autowired SchoolGroupService schoolGroupService) {
        this.studentService = studentService;
        this.schoolGroupService = schoolGroupService;
    }

    @GetMapping("/schoolGroup")
    public String schoolGroup(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("schoolGroups", schoolGroupService.getAllSchoolGroups());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "/schoolGroup/schoolGroup-all";
    }

    @GetMapping("/schoolGroup/add")
    public String schoolGroupAdd(Model model) {
        model.addAttribute("schoolGroup", new SchoolGroup(""));
        return "/schoolGroup/schoolGroup-add";
    }

    @GetMapping("/schoolGroup/delete/{id}")
    public ModelAndView deleteSchoolGroup(@PathVariable("id") String id, ModelMap model) {
        SchoolGroup schoolGroupOptional = schoolGroupService.findByIdWithStudents(id);
        if (schoolGroupOptional != null && schoolGroupOptional.getStudents().size() > 0) {
            model.addAttribute("errorMessage", "Transfer students to another group first");
            return new ModelAndView("redirect:/schoolGroup", model);
        }
        schoolGroupService.deleteById(id);
        model.addAttribute("successMessage", "Operacja się powiodła");
        return new ModelAndView("redirect:/schoolGroup", model);
    }

    @GetMapping("/schoolGroup/{id}")
    public String schoolGroupEditPath(@PathVariable("id") String id, Model model) {
        SchoolGroup schoolGroup = schoolGroupService.findByIdWithStudents(id);

        if (schoolGroup != null) {
            model.addAttribute("schoolGroup", schoolGroup);
        } else {
            model.addAttribute("schoolGroup", new SchoolGroup(""));
        }

        return "/schoolGroup/schoolGroup-edit";
    }

    @PostMapping("/schoolGroup/{id}")
    public ModelAndView schoolGroupEdit(@PathVariable("id") String id, @Valid @ModelAttribute SchoolGroup editedSchoolGroup,Errors errors, ModelMap model) {
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.getDefaultMessage());
            }
        } else {
            SchoolGroup schoolGroup = schoolGroupService.findById(id);
            if (schoolGroup != null) {
                schoolGroupService.editSchoolGroup(editedSchoolGroup);
            }
            model.addAttribute("successMessage", "User edited! :)");
        }

        return new ModelAndView("redirect:/schoolGroup", model);
    }

    @PostMapping("/schoolGroup")
    public ModelAndView addNewSchoolGroup(@Valid SchoolGroup schoolGroup, Errors errors, ModelMap model) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.getDefaultMessage());
            }
        } else {
            schoolGroupService.addSchoolGroup(schoolGroup);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/schoolGroup", model);
    }
}
