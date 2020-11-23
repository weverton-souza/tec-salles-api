package com.tec.salles.dto;

import java.io.Serializable;

public class Login implements Serializable {
    private String username;
    private String password;

    public Login() {}

    public String getUsername() {
        return username;
    }

    public Login setUsername(final String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }
}
