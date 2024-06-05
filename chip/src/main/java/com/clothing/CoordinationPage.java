package com.clothing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CoordinationPage extends JPanel {
    private MainApp mainApp;
    private JSONObject imageMetadata;

    public CoordinationPage(MainApp mainApp) {
        this.mainApp = mainApp;
        loadMetadata();
        setupUI();
    }

    private void loadMetadata() {
        try {
            String jsonPath = "src/main/resources/public/img/Clothing/image_metadata.json";
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonPath)));
            imageMetadata = new JSONObject(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "메타 데이터 로드 실패", "오류", JOptionPane.ERROR_MESSAGE); 
            imageMetadata = new JSONObject(); // 오류 시 빈 JSON 객체 생성
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel();
        JButton randomizeButton = new JButton("Randomize Outfit");
        randomizeButton.addActionListener(e -> displayRandomImage(imageLabel));

        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(backButton);
        buttonPanel.add(randomizeButton);

        add(new JLabel("Coordination Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);
    }

    private void displayRandomImage(JLabel imageLabel) {
        String[] categories = new String[]{"Tops", "Bottoms", "Shoes", "Accessories"};
        Random rand = new Random();
        String selectedCategory = categories[rand.nextInt(categories.length)];

        JSONArray categoryImages = imageMetadata.optJSONArray(selectedCategory.toLowerCase());
        if (categoryImages != null && categoryImages.length() > 0) {
            String imageName = categoryImages.getString(rand.nextInt(categoryImages.length()));
            String imagePath = "src/main/resources/public/img/Clothing/" + selectedCategory.toLowerCase() + "/" + imageName;
            ImageIcon icon = new ImageIcon(imagePath);
            imageLabel.setIcon(icon);
        } else {
            imageLabel.setText("해당 카테고리에서 이미지를 찾을 수 없습니다.");
        }
        validate();
    }
}
