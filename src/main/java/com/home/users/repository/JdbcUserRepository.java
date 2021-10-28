package com.home.users.repository;

import com.home.users.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository //создаст бин классса и внедрит его в контроллер
public class JdbcUserRepository implements UserRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/users";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private final Connection connection;

    public JdbcUserRepository() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void createUser(User user) {
        try {
            String SQL = "INSERT INTO users VALUES(" + user.getId() + ",'"
                    + user.getFirstname() +
                    "')";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return users;
    }

    @Override
    public User getUser(long id) {
        User user = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getLong("id"
            ), resultSet.getString("name"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteUser(long id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public User updateUser(long id, String name) {
        User newUser = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE users SET name=? WHERE id=?");
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);

            newUser = new User(id, name);

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newUser;
    }
}
