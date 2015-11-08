package com.nishtahir.utils;

import com.nishtahir.value.IntegerValue;
import com.nishtahir.value.Value;

/**
 * Created by Nish on 11/8/15.
 */
public class ValueUtils {

    public static IntegerValue checkAsInt(Value value) throws IllegalArgumentException{
        if (!value.getClass().isAssignableFrom(IntegerValue.class)) {
            throw new IllegalArgumentException();
        }
        return (IntegerValue) value;
    }
}
