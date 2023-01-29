package com.projekt.planLekcji.Lesson;

import com.projekt.planLekcji.Lesson.Lesson;
import com.projekt.planLekcji.Lesson.LessonService;
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
public class LessonWebController {

    private final StudentService studentService;
    private final LessonService lessonService;

    public LessonWebController(@Autowired StudentService studentService, @Autowired LessonService lessonService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @GetMapping("/lesson")
    public String lesson(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("lessons", lessonService.getAllLessons());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "/lesson/lesson-all";
    }

    @GetMapping("/lesson/add")
    public String lessonAdd(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "/lesson/lesson-add";
    }

    @GetMapping("/lesson/delete/{id}")
    public ModelAndView deleteLesson(@PathVariable("id") String id, ModelMap model) {
        lessonService.deleteById(id);
        model.addAttribute("successMessage", "Operacja się powiodła");
        return new ModelAndView("redirect:/lesson", model);
    }

    @GetMapping("/lesson/{id}")
    public String lessonEditPath(@PathVariable("id") String id, Model model) {
        Lesson lesson = lessonService.findById(id);

        if (lesson != null) {
            model.addAttribute("lesson", lesson);
        } else {
            model.addAttribute("lesson", new Lesson());
        }

        return "/lesson/lesson-edit";
    }

    @PostMapping("/lesson/{id}")
    public String lessonEdit(@PathVariable("id") String id, @ModelAttribute Lesson editedLesson, Model model) {
        Lesson lesson = lessonService.findById(id);
        if (lesson != null) {
            lessonService.editLesson(editedLesson);
        }

        return "redirect:/lesson";
    }

    @PostMapping("/lesson")
    public ModelAndView addNewLesson(@Valid Lesson lesson, Errors errors, ModelMap model) {
        System.out.println(errors.getAllErrors());
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.toString());
            }
        } else {
            lessonService.addLesson(lesson);
            model.addAttribute("successMessage", "User added! :)");
        }

        return new ModelAndView("redirect:/lesson", model);
    }
}
