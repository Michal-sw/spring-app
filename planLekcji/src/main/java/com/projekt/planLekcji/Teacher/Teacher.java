package com.projekt.planLekcji.Teacher;

import com.projekt.planLekcji.Group.Group;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teacher")
public class Teacher {
    @NotNull(message = "First name must be specified!")
    @Size(min = 2)
    @Column
    private String firstName;
    @NotNull(message = "Last name must be specified!")
    @Size(min = 2)
    @Column
    private String lastName;

    @NotNull(message = "Speciality must be specified!")
    @Column
    private String speciality;

    @GeneratedValue
    @Id
    @Column
    private String id;


    @Column
    @ManyToMany
    private List<Group> assignedGroups = new ArrayList<Group>();

    public Teacher() {}

    public Teacher(String firstName, String lastName, String speciality) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
    }

    public void addGroup(Group group) {
        this.assignedGroups.add(group);
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
                .append("assigned groups - " + assignedGroups.toString() + " ");

        return result.toString();
    }
}
