package com.viktornar.github.petclinic.auth;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jwttoken;
    private String firstName;
    private String lastName;

    public JwtResponse(String jwttoken, String firstName, String lastName) {
        this.jwttoken = jwttoken;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public void setToken(String token) {
        this.jwttoken = token;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
