package com.nhs.healthcosteligibility.model;

public enum Frequency {

    WEEK(1, "Weekly"),
    TWO_WEEK(2, "Two Weekly"),
    FOUR_WEEK(4, "Four Weekly"),
    MONTH(1, "Monthly"),
    QUARTER(13, "Quarterly"),
    YEAR(52, "Yearly");

    private final Integer value;
    private final String text;

    private Frequency(final Integer value, final String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
}
