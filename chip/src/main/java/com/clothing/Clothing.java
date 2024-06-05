package com.clothing;

public class Clothing {
    private int id; // 고유 식별자
    private String name; // 옷의 이름
    private String color; // 옷의 색상
    private String season; // 계절
    private String style; // 스타일
    private String laundry; // 세탁 방법
    private String category; // 카테고리 (예: 상의, 하의 등)
    private String imagePath;  // 이미지 경로 필드 추가

    // 모든 필드를 매개변수로 받는 생성자
    public Clothing(int id, String name, String color, String season, String style, String laundry, String category, String imagePath) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.season = season;
        this.style = style;
        this.laundry = laundry;
        this.category = category;
        this.imagePath = imagePath;  // 이미지 경로를 초기화
    }

    // 각 필드에 대한 getter 메서드
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSeason() {
        return season;
    }

    public String getStyle() {
        return style;
    }

    public String getLaundry() {
        return laundry;
    }

    public String getCategory() {
        return category;
    }

    public String getImagePath() {
        return imagePath;
    }
    
}