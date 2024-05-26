package com.clothing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:h2:./clothingdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public DatabaseManager() {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "password VARCHAR(255))");
            stmt.execute();
            stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS clothing (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "color VARCHAR(255), " +
                "season VARCHAR(255), " +
                "style VARCHAR(255), " +
                "laundry VARCHAR(255), " +
                "category VARCHAR(255))");
            stmt.execute();
            stmt.close();

            // 기본 사용자 추가
            addUser(conn, new User(0, "noonsong", "smwu@sookmyung.ac.kr", "password"));

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public User authenticateUser(String email, String password) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(Connection conn, User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClothing(Clothing clothing) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO clothing (name, color, season, style, laundry, category) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, clothing.getName());
            stmt.setString(2, clothing.getColor());
            stmt.setString(3, clothing.getSeason());
            stmt.setString(4, clothing.getStyle());
            stmt.setString(5, clothing.getLaundry());
            stmt.setString(6, clothing.getCategory());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
