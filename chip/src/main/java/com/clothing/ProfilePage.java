package com.clothing;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {
    private MainApp mainApp;
    private String userEmail;

    public ProfilePage(MainApp mainApp, String userEmail) {
        this.mainApp = mainApp;
        this.userEmail = userEmail;
        setupUI();
        updateProfileInfo();  // 페이지 로드 시 사용자 정보를 업데이트
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        updateProfileInfo();  // 이메일 업데이트 시 정보를 새로고침
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JLabel profileLabel = new JLabel("프로필 정보", JLabel.CENTER);
        profileLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JTextArea profileInfo = new JTextArea();
        profileInfo.setEditable(false);
        profileInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());

        add(profileLabel, BorderLayout.NORTH);
        add(new JScrollPane(profileInfo), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateProfileInfo() {
        DatabaseManager dbManager = mainApp.getDatabaseManager();
        User user = dbManager.getUserByEmail(userEmail);

        StringBuilder userInfo = new StringBuilder();
        userInfo.append("이름: ").append(user.getName()).append("\n");
        userInfo.append("이메일: ").append(user.getEmail()).append("\n");

        JTextArea profileInfo = (JTextArea) getComponent(1); // Center component
        profileInfo.setText(userInfo.toString());
    }
}
