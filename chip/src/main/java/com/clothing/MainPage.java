package com.clothing;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    private MainApp mainApp;

    public MainPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JButton closetButton = new JButton("Closet");
        closetButton.addActionListener(e -> mainApp.showClosetPage());

        JButton coordinationButton = new JButton("Coordination");
        coordinationButton.addActionListener(e -> mainApp.showCoordinationPage());

        JButton laundryGuideButton = new JButton("Laundry Guide");
        laundryGuideButton.addActionListener(e -> mainApp.showLaundryGuidePage());

        JButton myPageButton = new JButton("My Page");
        myPageButton.addActionListener(e -> mainApp.showMyPage());

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(e -> mainApp.showProfilePage());

        JButton communityButton = new JButton("Community");
        communityButton.addActionListener(e -> mainApp.showCommunityPage());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.add(closetButton);
        buttonPanel.add(coordinationButton);
        buttonPanel.add(laundryGuideButton);
        buttonPanel.add(myPageButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(communityButton);

        add(new JLabel("Main Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
