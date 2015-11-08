package com.nishtahir.utils;

/**
 * Created by Nish on 11/7/15.
 */
public class StringUtils {

    /**
     * Trim and remove trailing quotes
     * @param value
     * @return
     */
    public static String clean(String value){
        value = value.trim();
        return value.substring(1, value.length()-1);
    }
}
