package com.example.utils.enums;

import java.util.Random;

public enum Subjects {
    HINDI("Hindi"),
    ENGLISH("English"),
    MATHS("Maths"),
    PHYSICS("Physics"),
    CHEMISTRY("Chemistry"),
    BIOLOGY("Biology"),
    COMPUTER_SCIENCE("Computer Science"),
    COMMERCE("Commerce"),
    ACCOUNTING("Accounting"),
    ECONOMICS("Economics"),
    ARTS("Arts"),
    SOCIAL_STUDIES("Social Studies"),
    HISTORY("History"),
    CIVICS("Civics");

    private final String displayName;

    Subjects(String displayName) {
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
