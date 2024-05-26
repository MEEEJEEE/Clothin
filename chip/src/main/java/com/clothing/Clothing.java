package com.clothing;

public class Clothing {
    private String name;
    private String color;
    private String season;
    private String style;
    private String laundry;
    private String category;

    public Clothing(String name, String color, String season, String style, String laundry, String category) {
        this.name = name;
        this.color = color;
        this.season = season;
        this.style = style;
        this.laundry = laundry;
        this.category = category;
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
}
