package com.clothing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClosetPage extends JPanel {
    private final MainApp mainApp;
    private Map<String, java.util.List<Clothing>> clothingCategories;

    public ClosetPage(MainApp mainApp) {
        this.mainApp = mainApp;
        clothingCategories = new HashMap<>();
        clothingCategories.put("상의", new java.util.ArrayList<>());
        clothingCategories.put("하의", new java.util.ArrayList<>());
        clothingCategories.put("외투", new java.util.ArrayList<>());
        clothingCategories.put("액세서리/모자", new java.util.ArrayList<>());
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JButton addButton = new JButton("옷 추가");
        addButton.addActionListener(e -> mainApp.showAddClothingPage());

        JButton detailButton = new JButton("옷 상세 정보");
        detailButton.addActionListener(e -> mainApp.showClothingDetailPage());

        JButton backButton = new JButton("메인 화면으로 돌아가기");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(detailButton);
        buttonPanel.add(backButton);

        add(new JLabel("내 옷장 관리", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
