package com.clothing;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {
    private MainApp mainApp;

    public ProfilePage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        User user = mainApp.getCurrentUser();

        ImageIcon profileImageIcon = new ImageIcon(user.getProfileImagePath());
        Image profileImage = profileImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledProfileImageIcon = new ImageIcon(profileImage);
        JLabel profileImageLabel = new JLabel(scaledProfileImageIcon);

        JTextArea userInfo = new JTextArea(10, 40);
        userInfo.setText("마이페이지\n\n");
        userInfo.append("이름: " + user.getName() + "\n");
        userInfo.append("아이디: " + user.getId() + "\n");
        userInfo.append("이메일: " + user.getEmail() + "\n");

        userInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userInfo);

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel infoPanel = new JPanel();
        infoPanel.add(profileImageLabel, BorderLayout.WEST);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        add(infoPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}

//데이터베이스에서 오류 수정중(윤준영)