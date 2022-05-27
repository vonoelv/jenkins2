package com.example.utils.enums;

import java.util.Random;

public enum NCR {
    DELHI("Delhi"),
    GURGAON("Gurgaon"),
    NOIDA("Noida");

    private final String displayName;

    NCR(String displayName) {
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