package com.home.users.controller;

public class CreateUserRequest {
    long id;
    String name;

    public CreateUserRequest(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
