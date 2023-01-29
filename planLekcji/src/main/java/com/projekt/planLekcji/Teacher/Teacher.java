package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.Lesson.Lesson;
import org.hibernate.annotations.GenericGenerator;

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
    private Speciality speciality;

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons = new ArrayList<>();

    public Teacher() {}

    public Teacher(String firstName, String lastName, Speciality speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
    public void removeLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }

    public String getId() { return id; }
    public List<Lesson> getLessons() { return this.lessons; }

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
    public Speciality getSpeciality() {
        return speciality;
    }
    public void setSpeciality(Speciality speciality) {
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
