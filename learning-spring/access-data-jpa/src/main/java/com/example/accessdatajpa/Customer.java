package com.example.accessdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    // indicate that the ID should be generated automatically.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    // The default constructor exist only for the sake of JPA. You do not use it directly, so it is designated as protected.
    protected Customer() {}

    // use to create instance of Customer to be saved to the database.
    public Customer (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id='%d', firstName='%s', lastName='%s']",
                id, firstName, lastName );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
