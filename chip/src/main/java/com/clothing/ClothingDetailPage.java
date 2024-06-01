package com.clothing;

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
        predefinedClothings.add(new Clothing("액세서리G", "액세서리"));
        predefinedClothings.add(new Clothing("액세서리H", "액세서리"));

    private void setupUI(Clothing clothing) {
        setLayout(new BorderLayout()); // BorderLayout 사용
         
        // 상세 정보를 보여주는 레이블들 초기화 및 설정
        nameLabel = new JLabel("Name: " + clothing.getName());
        colorLabel = new JLabel("Color: " + clothing.getColor());
        seasonLabel = new JLabel("Season: " + clothing.getSeason());
        styleLabel = new JLabel("Style: " + clothing.getStyle());
        laundryLabel = new JLabel("Laundry: " + clothing.getLaundry());
        categoryLabel = new JLabel("Category: " + clothing.getCategory());

        // 정보 패널 구성
        JPanel infoPanel = new JPanel(new GridLayout(6, 1)); // GridLayout 수정
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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 버튼 패널 수정
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(new JLabel("Clothing Detail Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Predefined items를 보여주는 메소드 (옵션)
    public void displayPredefinedItems() {
        for (Clothing item : predefinedClothings) {
            this.add(new JLabel(item.getName() + " (" + item.getType() + ")"));
        }
    }
}