package com.home.users.controller;

import com.home.users.model.User;
import com.home.users.repository.FileUserRepository;
import com.home.users.repository.UserRepository;
import com.home.users.service.UserService;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class UserController {
    private static final Random RANDOM = new Random();

    public void run() throws IOException {
        UserRepository userRepository = new FileUserRepository();
        UserService userService = new UserService(userRepository);
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Создать пользователя");
            System.out.println("2) Вывести список всех пользователей");
            System.out.println("3) Получить пользователя по идентификатору");
            System.out.println("4) Удалить пользователя по идетификатору");
            System.out.println("5) Обновить данные пользователя");
            System.out.println("6) Выйти из программы");
            int choice = s.nextInt();
            s.nextLine();
            if (choice == 6) {
                break;
            }
            switch (choice) {
                case 1:
                    long id = RANDOM.nextLong();
                    System.out.println("Введите имя пользователя");
                    String name = s.nextLine();
                    User user = new User(id, name);
                    userRepository.createUser(user);
                    System.out.println("Идентификатор пользователя: " + id);
                    break;
                case 2:
                    System.out.println("Список всех пользователей");
                    userRepository.getAllUsers().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Введите идентификатор пользователя");
                    long idGet = s.nextLong();
                    userRepository.getUser(idGet);
                    break;
                case 4:
                    System.out.println("Введите идентификатор пользователя");
                    long idRemove = s.nextLong();
                    userRepository.deleteUser(idRemove);
                    break;
                case 5:
                    System.out.println("Введите идентификатор пользователя");
                    long idUpdate = s.nextLong();
                    System.out.println("Введите новое имя пользователя");
                    s.nextLine();
                    String newName = s.nextLine();
                    userService.updateUser(idUpdate, newName);
                    break;
            }
        }
    }
}
