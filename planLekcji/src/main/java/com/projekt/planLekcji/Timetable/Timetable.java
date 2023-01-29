package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Lesson.Lesson;

import javax.persistence.*;
import java.util.List;

@Entity
public class Timetable {

    @Id
    @GeneratedValue
    private String id;

    @OneToOne
    private SchoolGroup assignedToSchoolGroup;

    @OneToMany(mappedBy = "timetable")
    private List<Lesson> lessons;

    public Timetable() { }


    public SchoolGroup getAssignedSchoolGroup() {
        return assignedToSchoolGroup;
    }

    public void setAssignedToSchoolGroup(SchoolGroup assignedToSchoolGroup) {
        this.assignedToSchoolGroup = assignedToSchoolGroup;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
