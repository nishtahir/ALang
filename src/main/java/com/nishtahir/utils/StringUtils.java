package com.nishtahir.utils;

/**
 * Utility class to help with {@link String}
 */
public class StringUtils {

    /**
     * Trim and remove trailing quotes
     * @param value String to clean
     * @return clean String
     */
    public static String clean(String value){
        value = value.trim();
        return value.substring(1, value.length()-1);
    }
}
