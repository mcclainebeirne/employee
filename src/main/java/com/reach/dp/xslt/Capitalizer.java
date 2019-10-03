package com.reach.dp.xslt;

public class Capitalizer {
    /**
     * This method capitalizes the first character
     * in the provided string.
     * @return modified string
     */
    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
