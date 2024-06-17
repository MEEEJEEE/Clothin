package com.clothing;

import java.util.List;
import java.util.Random;

public class ClothingRecommendation {
    private DatabaseManager dbManager;

    public ClothingRecommendation(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public Clothing recommendClothing(String weather, String occasion) {
        List<Clothing> clothingItems = dbManager.getAllClothing();
        if (clothingItems.isEmpty()) {
            return null;
        }

        // Simple recommendation logic based on weather and occasion
        // This can be enhanced with more complex logic and AI integration
        Random random = new Random();
        return clothingItems.get(random.nextInt(clothingItems.size()));
    }
}
