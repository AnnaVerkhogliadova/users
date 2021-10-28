package com.home.users.controller;

import com.home.users.dto.UserRequest;
import com.home.users.dto.UserResponse;
import com.home.users.model.User;
import com.home.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired //внедрение
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("id") long id) throws IOException {

        User user = userService.getUser(id);
        return new UserResponse(user.getId(), user.getFirstname());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") long id) throws IOException {
        userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable("id") long id,
                                   @RequestBody UserRequest request) throws IOException {
        User user = userService.updateUser(id, request.getName());

        return new UserResponse(user.getId(), user.getFirstname());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponse create(@RequestBody UserRequest request) {
        User user = userService.createUser(request.getName());

        return new UserResponse(user.getId(), user.getFirstname());

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() throws IOException {
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstname()))
                .collect(Collectors.toList());
    }
}
