package com.example.utils.enums;

import java.util.Random;

public enum Rajasthan {
    JAIPUR("Jaipur"),
    JAISELMER("Jaiselmer");

    private final String displayName;

    Rajasthan(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static String getRandomDisplayName() {
        return values()[new Random().nextInt(values().length)].toString();
    }
}
