package com.home.users.controller;

public class UserRequest {
    long id;
    String name;

    public UserRequest(long id, String name) {
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
