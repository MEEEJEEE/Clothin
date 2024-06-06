package com.clothing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:h2:./clothingdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // 생성자에서 데이터베이스 초기화 메서드 호출
    public DatabaseManager() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        // 데이터베이스 연결 및 테이블 생성
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            // 사용자 테이블 생성
            stmt.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255))");
            // 의류 테이블 생성
            stmt.execute("CREATE TABLE IF NOT EXISTS clothing (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), color VARCHAR(255), season VARCHAR(255), type VARCHAR(255), category VARCHAR(255), imagePath VARCHAR(255))");
            // 게시물 테이블 생성
            stmt.execute("CREATE TABLE IF NOT EXISTS posts (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), content TEXT, author VARCHAR(255))");
        } catch (SQLException e) {
            System.err.println("데이터베이스 초기화 중 오류 발생: " + e.getMessage());
        }
    }
/* 
    public DatabaseManager() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "password VARCHAR(255))");
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS clothing (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "color VARCHAR(255), " +
                "season VARCHAR(255), " +
                "type VARCHAR(255), " +
                "category VARCHAR(255))");
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS posts (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255), " +
                "content TEXT, " +
                "author VARCHAR(255))");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 테이블 생성을 담당하는 메서드
    private void createTables(Statement stmt) throws SQLException {
        // 사용자 테이블 생성
        stmt.execute(
            "CREATE TABLE IF NOT EXISTS users (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255), " +
            "email VARCHAR(255), " +
            "password VARCHAR(255))");
        
        // 의류 테이블 생성
        stmt.execute(
            "CREATE TABLE IF NOT EXISTS clothing (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255), " +
            "color VARCHAR(255), " +
            "season VARCHAR(255), " +
            "type VARCHAR(255), " +
            "category VARCHAR(255))");

            // 게시물 테이블 생성
        stmt.execute(
            "CREATE TABLE IF NOT EXISTS posts (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "title VARCHAR(255), " +
            "content TEXT, " +
            "author VARCHAR(255))");
    }
*/

    // 데이터베이스 연결을 반환하는 메서드
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 사용자 인증 메서드
    public User authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("사용자 인증 중 오류 발생: " + e.getMessage());
        }
        return null;
    }

    public void addUser(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClothing(Clothing clothing) {
        String query = "INSERT INTO clothing (name, color, season, type, category, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, clothing.getName());
            stmt.setString(2, clothing.getColor());
            stmt.setString(3, clothing.getSeason());
            stmt.setString(4, clothing.getType());
            stmt.setString(5, clothing.getCategory());
            stmt.setString(6, clothing.getImagePath());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("의류 항목 추가 중 오류 발생: " + e.getMessage());
        }
    }

    public List<Clothing> getClothingByCategory(String category) {
        List<Clothing> clothingItems = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clothing WHERE category = ?");
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clothingItems.add(new Clothing(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getString("season"),
                    rs.getString("type"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothingItems;
    }

    public List<Clothing> getAllClothing() {
        List<Clothing> clothingItems = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clothing");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clothingItems.add(new Clothing(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getString("season"),
                    rs.getString("type"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothingItems;
    }

    public void addPost(Post post) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO posts (title, content, author) VALUES (?, ?, ?)");
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 예외 처리 로직 추가
    public void addUser(User user) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            int result = stmt.executeUpdate();
            if (result == 0) {
                throw new SQLException("사용자 추가 실패");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 오류: " + e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("author")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public User getUserByEmail(String email) {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                rs.close();
                stmt.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
