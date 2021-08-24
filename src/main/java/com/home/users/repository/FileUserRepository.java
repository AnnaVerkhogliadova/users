package com.home.users.repository;

import com.home.users.model.User;
import com.home.users.util.UserParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUserRepository implements UserRepository {
    private File file;


    public FileUserRepository() {
        this.file = new File("src/main/resources/users.txt");
    }

    @Override
    public void createUser(User user) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(user.toString() + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return Files.lines(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8).map(UserParser::parse)
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(long id) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                User user = UserParser.parse(line);
                if (id == user.getId()) {
                    System.out.println(user);
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Пользователь не найден");
    }

    @Override
    public void deleteUser(long id) throws IOException {
        File tempFile = tempFile();
        new FileOutputStream(file).close();
        String line1;

        try (PrintWriter pw = new PrintWriter(file);
             BufferedReader brTemp = new BufferedReader(new FileReader(tempFile))) {

            //Прочитать из исходного файла и записать в новый
            // если контент не совпадает с данными, подлежащими удалению
            for (String line; (line = brTemp.readLine()) != null; ) {
                User user = UserParser.parse(line);
                if (id != user.getId()) {
                    pw.println(line);
                    pw.flush();
                }
            }
        }

        //Удалить исходный файл
        if (!tempFile.delete()) {
            System.out.println("Could not delete file");
        }

    }

    @Override
    public User updateUser(long id, String name) throws IOException {
        File tempFile = tempFile();
        new FileOutputStream(file).close();
        User newUser = null;

        try (PrintWriter pw = new PrintWriter(file);
             BufferedReader brTemp = new BufferedReader(new FileReader(tempFile))) {
            for (String line; (line = brTemp.readLine()) != null; ) {
                User user = UserParser.parse(line);
                if (id != user.getId()) {
                    pw.println(line);
                } else {
                    newUser = new User(id, name);
                    pw.println(newUser);
                }
                pw.flush();
            }
        }

        if (!tempFile.delete()) {
            System.out.println("Could not delete file");
        }
        return newUser;
    }

    private File tempFile() throws IOException {
        File tempFile = new File("myTempFile.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {
            String line1;

            while ((line1 = br.readLine()) != null) {
                pw.println(line1);
                pw.flush();
            }
        }

        return tempFile;
    }
}
