package com.clothing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommunityPage extends JPanel {
    private MainApp mainApp;

    public CommunityPage(MainApp mainApp) {
        this.mainApp = mainApp;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));

        JTextArea postArea = new JTextArea();
        postArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(postArea);

        JTextField titleField = new JTextField();
        JTextArea contentArea = new JTextArea(5, 20);
        JTextField authorField = new JTextField();

        JButton postButton = new JButton("Post");
        postButton.addActionListener(e -> {
            String title = titleField.getText();
            String content = contentArea.getText();
            String author = authorField.getText();
            MainApp.getDatabaseManager().addPost(new Post(title, content, author));
            postArea.append("Title: " + title + "\nAuthor: " + author + "\n" + content + "\n\n");
        });

        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(e -> mainApp.showMainPage());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Content:"));
        inputPanel.add(new JScrollPane(contentArea));
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(postButton);
        buttonPanel.add(backButton);

        add(new JLabel("Community Page", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
