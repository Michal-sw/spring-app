package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Lesson.Lesson;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Timetable {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToOne
    private SchoolGroup assignedToSchoolGroup;

    @OneToMany()
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
    public void removeLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
