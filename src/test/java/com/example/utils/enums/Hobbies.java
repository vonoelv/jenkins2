package com.example.utils.enums;

import java.util.Random;

public enum Hobbies {
    SPORTS("Sports"),
    READING("Reading"),
    MUSIC("Music");

    private final String displayName;

    Hobbies(String displayName) {
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
