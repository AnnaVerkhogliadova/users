package com.home.users.service;

import com.home.users.model.User;
import com.home.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    private static final Random RANDOM = new Random();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        long id = RANDOM.nextLong();
        User user = new User(id, name);
        userRepository.createUser(user);
        System.out.println("Идентификатор пользователя: " + id);
        return user;
    }

    public List<User> getAllUsers() throws IOException {
        return userRepository.getAllUsers();
    }

    public User getUser(long getId) throws IOException {
        User user = userRepository.getUser(getId);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }
        return user;
    }

    public void deleteUser(long deleteId) throws IOException {
        userRepository.deleteUser(deleteId);
    }

    public User updateUser(long updateId, String name) throws IOException {
        getUser(updateId);
        User user = userRepository.updateUser(updateId, name);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        } else {
            System.out.println(user);
        }
        return user;
    }
}
