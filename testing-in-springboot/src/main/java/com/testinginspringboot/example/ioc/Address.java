package com.testinginspringboot.example.ioc;

/**
 * @author BYJ
 * @since 2024/09/26
 **/

public class Address {
    private String street;
    private int number;

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }

    // getters and setters
    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }
}
