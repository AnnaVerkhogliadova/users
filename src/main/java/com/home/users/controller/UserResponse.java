package com.home.users.controller;

public class UserResponse {
    private final long id;
    private final String firstname;

    public UserResponse(long id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
}
