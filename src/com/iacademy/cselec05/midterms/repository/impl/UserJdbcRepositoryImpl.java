package com.iacademy.cselec05.midterms.repository.impl;

import com.iacademy.cselec05.midterms.model.User;
import com.iacademy.cselec05.midterms.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepositoryImpl implements UserRepository {

    private static String JDBC_URL = "jdbc:mysql://localhost:3306/database";
    private static String JDBC_USER = "user";
    private static String JDBC_PASSWORD = "pass";

    public UserJdbcRepositoryImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC Driver not found");
        }
    }

    @Override
    public User findByUsername(String username) {

        String query = "SELECT id, username, password FROM user_info WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return user;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to the database");
        }
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO user_info (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();

            return findByUsername(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to the database");
        }
    }
}
