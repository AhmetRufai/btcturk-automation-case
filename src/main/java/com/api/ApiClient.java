package com.api;

public abstract class ApiClient {

    public String isBlankOrNull(String str) {
        if (str.equalsIgnoreCase("BLANK")) {
            str = " ";
        } else if (str.equalsIgnoreCase("NULL")) {
            str = null;
        }
        return str;
    }

    public boolean isDouble(String str) {
        try {
            @SuppressWarnings("unused")
            double x = Double.parseDouble(str);
            return true; //String is an Double
        } catch (NumberFormatException e) {
            return false; //String is not an Double
        }
    }

    public boolean isInt(String str) {
        try {
            @SuppressWarnings("unused")
            double x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }
}
