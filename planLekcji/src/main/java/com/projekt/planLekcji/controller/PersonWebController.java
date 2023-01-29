package com.projekt.planLekcji.controller;

import com.projekt.planLekcji.SchoolGroup.*;
import com.projekt.planLekcji.Person.Person;
import com.projekt.planLekcji.Person.PersonService;
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
public class PersonWebController {

    private final PersonService personService;
    private final SchoolGroupService schoolGroupService;

    public PersonWebController(@Autowired PersonService personService, @Autowired SchoolGroupService schoolGroupService) {
        this.personService = personService;
        this.schoolGroupService = schoolGroupService;
    }

    @GetMapping("/person")
    public String persons(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("allPersonsFromDB", personService.getAllPersons());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "person-all";
    }

    @GetMapping("/person/add")
    public String personAdd(Model model) {
        model.addAttribute("person", new Person("", "", null));
        model.addAttribute("schoolGroups", schoolGroupService.getAllGroups());
        return "person-add";
    }

    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable("id") String id, Model model) {
        personService.deleteById(id);
        model.addAttribute("successMessage", "Operacja się powiodła");
        return "redirect:/person";
    }

    @GetMapping("/person/{id}")
    public String personEditPath(@PathVariable("id") String id, Model model) {
        Person person = personService.findById(id);
        if (person != null) {
            model.addAttribute("person", person);
            model.addAttribute("schoolGroups", schoolGroupService.getAllGroups());
        } else {
            model.addAttribute("person", new Person("", "",null));
        }

        return "person-edit";
    }

    @PostMapping("/person/{id}")
    public String personEdit(@PathVariable("id") String id, @ModelAttribute Person editedPerson, Model model) {
        Person person = personService.findById(id);
        if (person != null) {
            personService.editPerson(editedPerson);
        }

        return "redirect:/person";
    }

    @PostMapping("/person")
    public ModelAndView addNewPerson(@Valid Person person, Errors errors, ModelMap model) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.toString());
            }
            model.addAttribute("errorMessage", "Data not valid, try again :)");
        } else {
            personService.addPerson(person);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/person", model);
    }
}
