package com.home.users.requestAndResponse;

public class UserResponse {
    private final long id;
    private final String name;

    public UserResponse(long id, String name) {
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
