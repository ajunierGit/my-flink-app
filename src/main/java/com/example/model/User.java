package com.example.model;

public class User {
    public int id;
    public String name;
    public boolean active;

    // Default constructor required for Jackson
    public User() {}

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', active=" + active + "}";
    }
}