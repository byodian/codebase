package com.testinginspringboot.example.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author BYJ
 * @since 2024/09/26
 **/

@Component
public class Company {
    private Address address;
    public Company(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
