package com.clothing;

import static spark.Spark.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainApp {
    private static MainApp instance;
    private DatabaseManager databaseManager;
    private ClothingRecommendation clothingRecommendation;
    private User currentUser;

    private MainApp() {
        databaseManager = new DatabaseManager();
        clothingRecommendation = new ClothingRecommendation(databaseManager);
    }

    public static MainApp getInstance() {
        if (instance == null) {
            instance = new MainApp();
        }
        return instance;
    }

    public static DatabaseManager getDatabaseManager() {
        return getInstance().databaseManager;
    }

    public static User getCurrentUser() {
        return getInstance().currentUser;
    }

    public static void setCurrentUser(User user) {
        getInstance().currentUser = user;
    }

    public static void main(String[] args) {
        port(4567);
        staticFiles.location("/public");

        // 웹 페이지 경로 설정
        get("/", (req, res) -> {
            res.redirect("/1_intro.html");
            return null;
        });

        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            User user = getDatabaseManager().authenticateUser(email, password);
            if (user != null) {
                setCurrentUser(user);
                res.redirect("/3_wardrobe(mainpage).html");
            } else {
                res.status(401);
                return "로그인 실패!";
            }
            return null;
        });

        post("/signup", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            getDatabaseManager().addUser(new User(0, name, email, password));
            res.redirect("/2_login.html");
            return null;
        });

        post("/add-clothing", (req, res) -> {
            String name = req.queryParams("name");
            String color = req.queryParams("color");
            String season = req.queryParams("season");
            String type = req.queryParams("type");
            String category = req.queryParams("category");

            getDatabaseManager().addClothing(new Clothing(0, name, color, season, type, category));
            res.redirect("/3_wardrobe(mainpage).html");
            return null;
        });

        post("/community", (req, res) -> {
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            String author = req.queryParams("author");

            getDatabaseManager().addPost(new Post(0, title, content, author));
            res.redirect("/3_community_board.html");
            return null;
        });

        // Example usage of clothingRecommendation for weather-based recommendation
        get("/recommendation", (req, res) -> {
            String weather = req.queryParams("weather");
            String occasion = req.queryParams("occasion");
            Clothing recommendation = getInstance().clothingRecommendation.recommendClothing(weather, occasion);
            if (recommendation != null) {
                return "Recommended clothing: " + recommendation.getName() + " (" + recommendation.getCategory() + ")";
            } else {
                return "No recommendation available.";
            }
        });
    }

    public void showMainPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new MainPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showClosetPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Closet Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ClosetPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showAddClothingPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Clothing Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new AddClothingPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showClothingDetailPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Clothing Detail Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ClothingDetailPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showCoordinationPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Coordination Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new CoordinationPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showLaundryGuidePage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Laundry Guide Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new LaundryGuidePage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showMyPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new MyPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showProfilePage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Profile Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ProfilePage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showCommunityPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Community Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new CommunityPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }

    public void showLoginPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Login Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new LoginPage(this));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
