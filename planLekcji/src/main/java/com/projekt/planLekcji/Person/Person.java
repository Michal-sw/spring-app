package com.projekt.planLekcji.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Person {
    @NotNull(message = "First name must be specified!")
    @Size(min = 2)
    private String firstName;
    @NotNull(message = "Last name must be specified!")
    @Size(min = 2)
    private String lastName;
    private String email;

    @GeneratedValue
    @Id
    private String id;
    private String companyName = "";
    private String postalCode = "";
    private double incomes = 0;
    private String accept = "";
    private Date registrationDate;

    private UserType userType;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.id = String.valueOf(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = new Date();
        this.userType = UserType.Guest;
    }

    public String getId() { return id; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setAsAdmin() {
        this.userType = UserType.Administrator;
    }
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
    public void setAccept(boolean value) {
        this.accept = accept;
    };
    public void setIncome(double incomes) {
        this.incomes = incomes;
    };

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("First name - " + firstName + " ")
                .append("last name - " + lastName + " ")
                .append("email - " + email + " ")
                .append("company name - " + companyName + " ");

        return result.toString();
    }
}
