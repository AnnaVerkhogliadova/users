package com.home.users.controller;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserRequest {
    String name;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
