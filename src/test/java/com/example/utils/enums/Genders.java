package com.example.utils.enums;

import java.util.Random;

public enum Genders {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String displayName;

    Genders(String displayName) {
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
