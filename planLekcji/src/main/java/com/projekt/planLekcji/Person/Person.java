package com.projekt.planLekcji.Person;

import com.projekt.planLekcji.Group.Group;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @NotNull(message = "First name must be specified!")
    @Size(min = 2)
    @Column
    private String firstName;
    @NotNull(message = "Last name must be specified!")
    @Size(min = 2)
    @Column
    private String lastName;
    private String email;

    @GeneratedValue
    @Id
    @Column
    private String id;

    @NotNull(message = "Group must be specified!")
    @ManyToOne
    private Group schoolGroup;
    private String address = "";

    public Person() {}

    public Person(String firstName, String lastName, Group schoolGroup) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolGroup = schoolGroup;
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
    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSchoolGroup(Group schoolGroup) {
        this.schoolGroup = schoolGroup;
    };

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("First name - " + firstName + " ")
                .append("last name - " + lastName + " ")
                .append("group - " + schoolGroup.getName() + " ");

        return result.toString();
    }
}
