package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.Lesson.Lesson;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Teacher {
    @NotNull(message = "First name must be specified!")
    @Size(min = 2)
    private String firstName;
    @NotNull(message = "Last name must be specified!")
    @Size(min = 2)
    private String lastName;

    @NotNull(message = "Speciality must be specified!")
    private String speciality;

    @GeneratedValue
    @Id
    private String id;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<Lesson>();

    public Teacher() {}

    public Teacher(String firstName, String lastName, String speciality) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    public String getId() { return id; }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("First name - " + firstName + " ")
                .append("last name - " + lastName + " ")
                .append("speciality - " + speciality + " ")
                .append("lessons - " + lessons.toString() + " ");

        return result.toString();
    }
}
