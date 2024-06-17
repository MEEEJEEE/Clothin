package com.clothing;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private MainApp mainApp;

    public LoginPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);


        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User user = MainApp.getDatabaseManager().authenticateUser(email, password);
            if (user != null) {
                MainApp.setCurrentUser(user);
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainApp.showMainPage();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Email or Password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1, 10, 10));
        buttonPanel.add(loginButton);

        add(new JLabel("Login Page", JLabel.CENTER), BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
