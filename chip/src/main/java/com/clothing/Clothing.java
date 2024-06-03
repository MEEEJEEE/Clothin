package com.clothing;

public class Clothing {
    private int id;
    private String name;
    private String color;
    private String season;
    private String type;
    private String category;

    public Clothing(int id, String name, String color, String season, String type, String category) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.season = season;
        this.type = type;
        this.category = category;
    }

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

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }
}
