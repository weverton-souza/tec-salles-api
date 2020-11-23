package com.tec.salles.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_access")
public class UserAccess {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private String roles;

    public UserAccess() {}

    public UserAccess(String id, String email, String username, String password, String roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public UserAccess setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAccess setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserAccess setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAccess setPassword(final String password) {
        this.password = password;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public UserAccess setRoles(final String roles) {
        this.roles = roles;
        return this;
    }
}
