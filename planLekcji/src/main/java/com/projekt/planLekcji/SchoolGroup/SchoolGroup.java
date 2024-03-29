package com.projekt.planLekcji.SchoolGroup;

import com.projekt.planLekcji.Student.Student;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SchoolGroup {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull(message = "Group must be specified!")
    @Size(min = 2, max = 2)
    @Pattern(regexp="^\\d[a-zA-Z]$", message = "Group name must be in {Number}{Letter} pattern")
    private String name;

    @OneToMany(mappedBy="schoolGroup")
    private List<Student> students = new ArrayList<>();

    public SchoolGroup(String name) {
        this.name = name;
    }

    public SchoolGroup() { }

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; };
    public void setName(String name) { this.name = name; };

    public List<Student> getStudents() { return students; };
    public void addStudent(Student student) { this.students.add(student); }
    public void removeStudent(Student student) { this.students.remove(student); }

}
