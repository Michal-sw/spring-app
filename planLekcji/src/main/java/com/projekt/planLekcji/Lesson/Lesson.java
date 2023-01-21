package com.projekt.planLekcji.Lesson;
import com.projekt.planLekcji.Teacher.Teacher;
import com.projekt.planLekcji.Timetable.Timetable;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    private Timetable timetable;

    @ManyToOne
    private Teacher teacher;

    private LocalTime startTime;

    private LocalTime endTime;

    public Lesson() { }


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timeTable) {
        this.timetable = timetable;
    }

}
