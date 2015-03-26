package com.semsari.domain;

/**
 * Created by Iman on 3/17/2015.
 */
public enum ItemPeriod {
    Free(29),
    Week(7),
    TwoWeek(14),
    Month(30),
    TreeMonth(90);


    private final int value;

    private ItemPeriod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
