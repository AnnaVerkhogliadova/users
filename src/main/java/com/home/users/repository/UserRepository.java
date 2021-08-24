package com.home.users.repository;

import com.home.users.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserRepository {
    void createUser(User user);

    List<User> getAllUsers() throws IOException;

    User getUser(long id) throws FileNotFoundException;

    void deleteUser(long id) throws IOException;

    User updateUser(long id, String name) throws IOException;
}
