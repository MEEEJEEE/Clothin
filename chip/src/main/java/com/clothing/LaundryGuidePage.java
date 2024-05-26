package com.clothing;

import javax.swing.*;
import java.awt.*;

public class LaundryGuidePage extends JPanel {
    private MainApp mainApp;

    public LaundryGuidePage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        buttonPanel.add(backButton);

        add(new JLabel("Laundry Guide Page", JLabel.CENTER), BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
