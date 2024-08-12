package com.byodian.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Combining multiple responsibilities related to the user
// 1. store users in a database you need a class to represent the persistent entity
// 2. Or retrieve users through a web service from another system, then you
// need a data transfer object to represent the user interfaces.
//@Entity
public class User {
//    @Id
    private Long id;
    private String username;
    private String password;
    private String authority;

    //getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }
}
