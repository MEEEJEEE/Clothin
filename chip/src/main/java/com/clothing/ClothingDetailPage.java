package com.clothing;

/*와일드카드(*)를 사용
import javax.swing.*;
import java.awt.*; */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClothingDetailPage extends JPanel {
    private final MainApp mainApp;
    private JLabel nameLabel, colorLabel, seasonLabel, styleLabel, laundryLabel, categoryLabel;
    private JButton nextButton;
    private ArrayList<Clothing> predefinedClothings;

    // MainApp 인스턴스와 Clothing 객체를 받는 생성자 추가
    public ClothingDetailPage(MainApp mainApp, Clothing clothing) {
        this.mainApp = mainApp;
        setupUI(clothing);
    }

    private void initializePredefinedClothings() {
        predefinedClothings = new ArrayList<>();
        predefinedClothings.add(new Clothing("상의A", "상의"));
        predefinedClothings.add(new Clothing("상의B", "상의"));
        predefinedClothings.add(new Clothing("하의C", "하의"));
        predefinedClothings.add(new Clothing("하의D", "하의"));
        predefinedClothings.add(new Clothing("신발E", "신발"));
        predefinedClothings.add(new Clothing("신발F", "신발"));
        predefinedClothings.add(new Clothing("액세서리Gd", "액세서리"));
        predefinedClothings.add(new Clothing("액세서리H", "액세서리"));

    //UI 설정
    private void setupUI(Clothing clothing) {
        setLayout(new BorderLayout()); // BorderLayout 사용
         
        nameLabel = new JLabel("Name: " + Optional.ofNullable(clothing.getName()).orElse("정보 없음"));
        colorLabel = new JLabel("Color: " + Optional.ofNullable(clothing.getColor()).orElse("정보 없음"));
        seasonLabel = new JLabel("Season: " + Optional.ofNullable(clothing.getSeason()).orElse("정보 없음"));
        styleLabel = new JLabel("Style: " + Optional.ofNullable(clothing.getStyle()).orElse("정보 없음"));
        laundryLabel = new JLabel("Laundry: " + Optional.ofNullable(clothing.getLaundry()).orElse("정보 없음"));
        categoryLabel = new JLabel("Category: " + Optional.ofNullable(clothing.getCategory()).orElse("정보 없음"));


        // 정보 패널 구성
        JPanel infoPanel = new JPanel(new GridLayout(6, 1));
        infoPanel.add(nameLabel);
        infoPanel.add(colorLabel);
        infoPanel.add(seasonLabel);
        infoPanel.add(styleLabel);
        infoPanel.add(laundryLabel);
        infoPanel.add(categoryLabel);

        add(infoPanel, BorderLayout.CENTER);

        // 다음 옷 보기 버튼
        nextButton = new JButton("다음 옷 보기");
        nextButton.addActionListener(e -> mainApp.showNextClothingDetail());
        nextButton.setVisible(false); // 기본적으로 보이지 않도록 설정
        add(nextButton, BorderLayout.SOUTH);

        //메인 화면으로 돌아가는 버튼
        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(new JLabel("Clothing Detail Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}