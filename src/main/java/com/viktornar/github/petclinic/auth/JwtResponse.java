package com.viktornar.github.petclinic.auth;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public void setToken(String token) {
        this.jwttoken = token;
    }
}
