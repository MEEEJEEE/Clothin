package com.clothing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class MainApp extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JList<Clothing> clothingList;
    private DefaultListModel<Clothing> clothingListModel;
    private JPanel mainPanel = new JPanel(cardLayout);
    private ArrayList<Clothing> allClothings = new ArrayList<>();
    private int currentClothingIndex = 0; // 현재 보여주고 있는 옷의 인덱스
    private User currentUser;
    private DatabaseManager databaseManager = new DatabaseManager();

    public MainApp() {
        setupUI();
        showLoginPage();
    }

    private void setupUI() {
        setTitle("클로징 Clothing");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setupClothingList();
        getContentPane().add(new JScrollPane(clothingList), BorderLayout.CENTER);
        pack();
        setVisible(true);
        JButton nextButton = new JButton("다음 옷 보기");
        nextButton.addActionListener(e -> showNextClothingDetail());
        add(nextButton, BorderLayout.SOUTH);

        mainPanel.add(new MainPage(this), "MainPage");
        mainPanel.add(new ClosetPage(this), "ClosetPage");
        mainPanel.add(new CoordinationPage(this), "CoordinationPage");
        mainPanel.add(new LaundryGuidePage(this), "LaundryGuidePage");
        mainPanel.add(new AddClothingPage(this), "AddClothingPage");
        mainPanel.add(new ClothingDetailPage(this, new Clothing("Example", "Blue", "Winter", "Formal", "Dry clean only", "Outerwear")), "ClothingDetailPage");
        mainPanel.add(new MyPage(this), "MyPage");
        mainPanel.add(new LoginPage(this), "LoginPage");

        add(mainPanel);
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void showLoginPage() {
        cardLayout.show(mainPanel, "LoginPage");
    }

    public void showMainPage() {
        cardLayout.show(mainPanel, "MainPage");
    }

    public void showClosetPage() {
        cardLayout.show(mainPanel, "ClosetPage");
    }

    public void showCoordinationPage() {
        cardLayout.show(mainPanel, "CoordinationPage");
    }

    public void showLaundryGuidePage() {
        cardLayout.show(mainPanel, "LaundryGuidePage");
    }

    public void showAddClothingPage() {
        cardLayout.show(mainPanel, "AddClothingPage");
    }

    public void showNextClothingDetail() {
        if (currentClothingIndex < allClothings.size() - 1) {
            currentClothingIndex++;  // 인덱스 증가
        } else {
            currentClothingIndex = 0; // 리스트 끝에 도달하면 처음으로
        }
        Clothing selectedClothing = allClothings.get(currentClothingIndex);
        updateClothingDetailPage(selectedClothing);
    }

    public void updateClothingDetailPage(Clothing clothing) {
        // 상세 페이지 내용 업데이트 로직
        ClothingDetailPage detailPage = new ClothingDetailPage(this, clothing);
        mainPanel.add(detailPage, "ClothingDetailPage");
        cardLayout.show(mainPanel, "ClothingDetailPage");
    }
    
    public void showClothingDetailPage() {
        // 이 메소드는 실제로 선택된 옷의 상세 페이지를 표시해야 함
        Clothing selectedClothing = getSelectedClothing();
        if (selectedClothing != null) {
            ClothingDetailPage detailPage = new ClothingDetailPage(this, selectedClothing);
            mainPanel.add(detailPage, "ClothingDetailPage");
            cardLayout.show(mainPanel, "ClothingDetailPage");
        } else {
            JOptionPane.showMessageDialog(this, "선택된 옷 정보가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showMyPage() {
        cardLayout.show(mainPanel, "MyPage");
    }

    private Clothing getSelectedClothing() {
        return clothingList.getSelectedValue();
    }
    private void setupClothingList() {
        clothingListModel = new DefaultListModel<>();
        for (Clothing clothing : allClothings) {
            clothingListModel.addElement(clothing);
        }
        clothingList = new JList<>(clothingListModel);
        clothingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
