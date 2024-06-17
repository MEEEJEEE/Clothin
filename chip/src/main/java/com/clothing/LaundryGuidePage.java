package com.clothing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LaundryGuidePage extends JPanel {
    private boolean waterWashable;
    private boolean dryCleanable;
    private boolean bleachUsable;
    private boolean ironable;
    private boolean handDryable;

    public LaundryGuidePage(MainApp mainApp) {
        setLayout(new GridLayout(6, 1));

        askQuestion("물세탁 가능한가요?", new String[]{"images/water_washable_yes.png", "images/water_washable_no.png"});
        askQuestion("드라이클리닝 가능한가요?", new String[]{"images/dry_clean_yes.png", "images/dry_clean_no.png"});
        askQuestion("표백제 사용 가능한가요?", new String[]{"images/bleach_yes.png", "images/bleach_no.png"});
        askQuestion("다림질 가능한가요?", new String[]{"images/iron_yes.png", "images/iron_no.png"});
        askQuestion("손으로 짜서 건조해도 되나요?", new String[]{"images/hand_dry_yes.png", "images/hand_dry_no.png"});

        JButton resultButton = new JButton("세탁 방법 확인하기");
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });
        add(resultButton);

        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());
        add(backButton);
    }

    private void askQuestion(String question, String[] imageFiles) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(question));

        JButton yesButton = createImageButton(imageFiles[0]);
        JButton noButton = createImageButton(imageFiles[1]);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLaundryOption(question, true);
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLaundryOption(question, false);
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        });

        panel.add(yesButton);
        panel.add(noButton);

        add(panel);
    }

    private void setLaundryOption(String question, boolean value) {
        if (question.contains("물세탁")) {
            waterWashable = value;
        } else if (question.contains("드라이클리닝")) {
            dryCleanable = value;
        } else if (question.contains("표백제")) {
            bleachUsable = value;
        } else if (question.contains("다림질")) {
            ironable = value;
        } else if (question.contains("손으로 짜서")) {
            handDryable = value;
        }
    }

    private JButton createImageButton(String imagePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath));
            if (img == null) {
                throw new IOException("이미지를 로드할 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("이미지를 로드할 수 없습니다: " + imagePath);
        }

        JButton button = new JButton();
        if (img != null) {
            Image scaledImage = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH); // 이미지 크기 조정
            ImageIcon icon = new ImageIcon(scaledImage);
            button.setIcon(icon);
        } else {
            button.setText("이미지 없음: " + imagePath);
        }
        button.setPreferredSize(new Dimension(100, 100));
        return button;
    }

    private void showResult() {
        StringBuilder result = new StringBuilder("세탁 방법:\n");
        result.append("물세탁 가능: ").append(waterWashable ? "예" : "아니오").append("\n");
        result.append("드라이클리닝 가능: ").append(dryCleanable ? "예" : "아니오").append("\n");
        result.append("표백제 사용 가능: ").append(bleachUsable ? "예" : "아니오").append("\n");
        result.append("다림질 가능: ").append(ironable ? "예" : "아니오").append("\n");
        result.append("손으로 짜서 말리기 가능: ").append(handDryable ? "예" : "아니오");

        JOptionPane.showMessageDialog(this, result.toString());

        saveResultToFile(result.toString());
    }

    private void saveResultToFile(String result) {
        File file = new File("laundry_guide_result.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(result);
            JOptionPane.showMessageDialog(this, "결과가 저장되었습니다: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "결과를 저장하는 중 오류가 발생했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Laundry Guide Page");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new LaundryGuidePage(null));  // For testing purposes, pass null as mainApp
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
