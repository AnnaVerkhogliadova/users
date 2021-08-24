package com.home;

import com.home.users.controller.UserController;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        new UserController().run();
    }

}
