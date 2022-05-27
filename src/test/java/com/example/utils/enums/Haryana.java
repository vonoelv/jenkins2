package com.example.utils.enums;

import java.util.Random;

public enum Haryana {
    KARNAL("Karnal"),
    PANIPAT("Panipat");

    private final String displayName;

    Haryana(String displayName) {
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
