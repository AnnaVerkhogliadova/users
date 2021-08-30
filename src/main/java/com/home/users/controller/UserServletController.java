package com.home.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.users.model.User;
import com.home.users.repository.FileUserRepository;
import com.home.users.repository.UserRepository;
import com.home.users.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServletController extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final UserService userService;


    public UserServletController() {
        final UserRepository userRepository = new FileUserRepository();
        userService = new UserService(userRepository);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        CreateUserRequest cur = OBJECT_MAPPER.readValue(body, CreateUserRequest.class);
        User user = userService.createUser(cur.getName());

        resp.setContentType("application/json; charset=UTF-8;");
        resp.setCharacterEncoding("UTF-8");
        String jsonString = OBJECT_MAPPER.writeValueAsString(user);
        resp.setStatus(200);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        List<User> users = userService.getAllUsers();

        resp.setContentType("application/json; charset=UTF-8;");
        resp.setCharacterEncoding("UTF-8");
        String jsonString = OBJECT_MAPPER.writeValueAsString(users);
        resp.setStatus(200);
        resp.getWriter().write(jsonString);
    }
}
