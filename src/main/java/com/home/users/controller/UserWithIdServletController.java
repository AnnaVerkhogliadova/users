package com.home.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.users.model.User;
import com.home.users.repository.JdbcUserRepository;
import com.home.users.repository.UserRepository;
import com.home.users.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class UserWithIdServletController extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final UserService userService;

    public UserWithIdServletController() {
        final UserRepository userRepository = new JdbcUserRepository();
        userService = new UserService(userRepository);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo(); // /{value}/test //запрос
        String[] pathParts = pathInfo.split("/");
        String part1 = pathParts[1]; // {value}

        long id = Long.parseLong(part1);
        User user = userService.getUser(id);
        UserResponse response = new UserResponse(user.getId(), user.getFirstname());

        String jsonString = OBJECT_MAPPER.writeValueAsString(response);
        resp.setStatus(200);
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String part1 = pathParts[1];
        long id = Long.parseLong(part1);
        userService.deleteUser(id);
        resp.setStatus(204);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String part1 = pathParts[1];

        long id = Long.parseLong(part1);
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        UserRequest ur = OBJECT_MAPPER.readValue(body, UserRequest.class);
        User user = userService.updateUser(id, ur.getName());
        UserResponse response = new UserResponse(user.getId(), user.getFirstname());
        String jsonString = OBJECT_MAPPER.writeValueAsString(response);
        resp.setStatus(200);
        resp.getWriter().write(jsonString);
    }
}
