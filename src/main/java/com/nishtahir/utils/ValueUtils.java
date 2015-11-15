package com.nishtahir.utils;

import com.nishtahir.value.IntegerValue;
import com.nishtahir.value.ListValue;
import com.nishtahir.value.Value;

/**
 * Utility class to help figure out a value's type
 */
public class ValueUtils {

    /**
     * Performs a type check and returns the {@link IntegerValue} cast
     * @param value value to Cast
     * @return {@link IntegerValue} cast of value
     * @throws IllegalArgumentException
     */
    public static IntegerValue asIntegerValue(Value value) throws IllegalArgumentException{
        if (!value.getClass().isAssignableFrom(IntegerValue.class)) {
            throw new IllegalArgumentException();
        }
        return (IntegerValue) value;
    }

    /**
     * Performs a type check and returns the {@link ListValue} cast
     * @param value value to Cast
     * @return {@link ListValue} cast of value
     * @throws IllegalArgumentException
     */
    public static ListValue asListValue(Value value) throws IllegalArgumentException{
        if (!value.getClass().isAssignableFrom(ListValue.class)) {
            throw new IllegalArgumentException();
        }
        return (ListValue) value;
    }
}
