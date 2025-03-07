package com.example.smartrecruit.model;

import com.example.smartrecruit.DAO.Role;

public class User {
    private int id;
    private String last_name, first_name, email, password, role;

    public User() {
    }

    public User(int id, String last_name, String first_name, String email, String password, String role) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String last_name, String first_name, String email, String password, String role) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String family_name) {
        this.last_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
