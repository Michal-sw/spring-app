package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.Group.Group;
import com.projekt.planLekcji.Lesson.Lesson;

import javax.persistence.*;
import java.util.List;

@Entity
public class Timetable {

    @Id
    @GeneratedValue
    private String id;

    @OneToOne
    private Group assignedToGroup;

    @OneToMany(mappedBy = "timetable")
    private List<Lesson> lessons;

    public Timetable() { }


    public Group getAssignedGroup() {
        return assignedToGroup;
    }

    public void setAssignedToGroup(Group assignedToGroup) {
        this.assignedToGroup = assignedToGroup;
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
