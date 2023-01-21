package com.projekt.planLekcji.Group;

import com.projekt.planLekcji.Person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Group {
    @GeneratedValue
    @Id
    private String id;

    @NotNull(message = "Group name must be in '{number}{Letter}' format!")
    @Size(min = 2, max = 2)
    private String name;

    @OneToMany(mappedBy="schoolGroup")
    private List<Person> students;

    public Group(String name) {
        this.name = name;
    }

    public Group() { }

    public String getId() { return id; }
    public String getName() { return name; };
    public List<Person> getStudents() { return students; };
    public void addStudent(Person student) { this.students.add(student); }
}
