package com.clothing;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClothingDetailPage extends JPanel {
    private final MainApp mainApp;
    private JLabel nameLabel, colorLabel, seasonLabel, styleLabel, laundryLabel, categoryLabel;

    // MainApp 인스턴스와 Clothing 객체를 받는 생성자 추가
    public ClothingDetailPage(MainApp mainApp, Clothing clothing) {
        this.mainApp = mainApp;
        setupUI(clothing);
    }

    private void setupUI(Clothing clothing) {
        setLayout(new BorderLayout(10, 10));
         
        // 상세 정보를 보여주는 레이블들 초기화 및 설정
        nameLabel = new JLabel("Name: " + clothing.getName());
        colorLabel = new JLabel("Color: " + clothing.getColor());
        seasonLabel = new JLabel("Season: " + clothing.getSeason());
        styleLabel = new JLabel("Style: " + clothing.getStyle());
        laundryLabel = new JLabel("Laundry: " + clothing.getLaundry());
        categoryLabel = new JLabel("Category: " + clothing.getCategory());

        // 정보 패널 구성
        JPanel infoPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        infoPanel.add(nameLabel);
        infoPanel.add(colorLabel);
        infoPanel.add(seasonLabel);
        infoPanel.add(styleLabel);
        infoPanel.add(laundryLabel);
        infoPanel.add(categoryLabel);

        //메인 화면으로 돌아가는 버튼
        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        buttonPanel.add(backButton);

        add(new JLabel("Clothing Detail Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
