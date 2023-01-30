package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.Lesson.Lesson;
import com.projekt.planLekcji.Lesson.LessonService;
import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.SchoolGroup.SchoolGroupService;
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
public class TimetableWebController {

    private final TimetableService timetableService;
    private final LessonService lessonService;
    private final SchoolGroupService schoolGroupService;

    public TimetableWebController(@Autowired SchoolGroupService schoolGroupService,
                                  @Autowired TimetableService timetableService,
                                  @Autowired LessonService lessonService
    ) {
        this.timetableService = timetableService;
        this.schoolGroupService = schoolGroupService;
        this.lessonService = lessonService;
    }

    @GetMapping("/timetable")
    public String timetable(
            @RequestParam(value = "errorMessage", required = false) String errorMessage,
            @RequestParam(value = "successMessage", required = false) String successMessage,
            Model model)
    {
        model.addAttribute("timetables", timetableService.getAllTimetables());
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);
        if (successMessage != null) model.addAttribute("successMessage", successMessage);

        return "/timetable/timetable-all";
    }

    @GetMapping("/timetable/add")
    public String timetableAdd(Model model) {
        model.addAttribute("timetable", new Timetable());
        model.addAttribute("schoolGroups", timetableService.findAllGroupsWithoutTimetable());
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "/timetable/timetable-add";
    }

    @GetMapping("/timetable/delete/{id}")
    public ModelAndView deleteTimetable(@PathVariable("id") String id, ModelMap model) {
        Timetable timetable = timetableService.findByIdWithAllLessons(id);
        for (Lesson lesson : timetable.getLessons()) {
            lessonService.setTimetable(lesson, null);
        }
        timetableService.deleteById(id);

        model.addAttribute("successMessage", "Operacja się powiodła");
        return new ModelAndView("redirect:/timetable", model);
    }

    @GetMapping("/timetable/{id}")
    public String timetableEditPath(@PathVariable("id") String id, Model model) {
        Timetable timetable = timetableService.findByIdWithAllLessons(id);
        if (timetable != null) {
            model.addAttribute("timetable", timetable);
        } else {
            model.addAttribute("timetable", new Timetable());
        }
        model.addAttribute("schoolGroups", timetableService.findAllGroupsWithoutTimetable());
        model.addAttribute("lessons", lessonService.getAllLessons());


        return "/timetable/timetable-edit";
    }

    @GetMapping("/timetable/{id}/lessons")
    public String timetableAllLessonsPath(@PathVariable("id") String id, Model model) {
        Timetable timetable = timetableService.findByIdWithAllLessons(id);
        if (timetable != null) {
            model.addAttribute("timetable", timetable);
        } else {
            model.addAttribute("timetable", new Timetable());
        }

        return "/timetable/timetable-all-lessons";
    }

    @PostMapping("/timetable/{id}")
    public String timetableEdit(@PathVariable("id") String id, @ModelAttribute Timetable editedTimetable, Model model) {
        Timetable timetable = timetableService.findByIdWithAllLessons(id);
        if (timetable != null) {
            timetableService.editTimetable(editedTimetable);
            for (Lesson lesson : timetable.getLessons()) {
                lessonService.setTimetable(lesson, timetable);
            }
        }

        return "redirect:/timetable";
    }

    @PostMapping("/timetable")
    public ModelAndView addNewTimetable(@Valid Timetable timetable, Errors errors, ModelMap model) {
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                model.addAttribute("errorMessage", error.getDefaultMessage());
            }
        } else {
            timetableService.addTimetable(timetable);
            for (Lesson lesson : timetable.getLessons()) {
                lessonService.setTimetable(lesson, timetable);
            }
            model.addAttribute("successMessage", "Timetable added! :)");
        }

        return new ModelAndView("redirect:/timetable", model);
    }
}
