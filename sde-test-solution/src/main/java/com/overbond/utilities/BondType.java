package com.overbond.utilities;

public enum BondType {

    GOVERNMENT("government"),
    CORPORATE("corporate");

    private final String strValue;

    BondType(String value) {
       this.strValue = value;
    }

    public String getStrValue() {
        return strValue;
    }
}
