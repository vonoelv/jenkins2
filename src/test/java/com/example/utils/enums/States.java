package com.example.utils.enums;

import java.util.Random;

public enum States {
    NCR("NCR"),
    HARYANA("Haryana"),
    RAJASTAN("Rajasthan");

    private final String displayName;

    States(String displayName) {
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
