package com.projekt.planLekcji.SchoolGroup;

import com.projekt.planLekcji.Person.Person;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolGroup {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull(message = "Group name must be in '{number}{Letter}' format!")
    @Size(min = 2, max = 3)
    private String name;

    @OneToMany(mappedBy="schoolGroup")
    private List<Person> students = new ArrayList<>();

    public SchoolGroup(String name) {
        this.name = name;
    }

    public SchoolGroup() { }

    public String getId() { return id; }
    public String getName() { return name; };
    public List<Person> getStudents() { return students; };
    public void addStudent(Person student) { this.students.add(student); }
}
