package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimetableController {
    private final TimetableService timetableService;

    public TimetableController(@Autowired TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/api/timetable/groupsWithoutTimetable")
    Iterable<SchoolGroup> getAll() {
        Iterable<SchoolGroup> schoolGroups = timetableService.findAllGroupsWithoutTimetable();
        return schoolGroups;
    }

}
