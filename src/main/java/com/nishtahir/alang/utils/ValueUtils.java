package com.nishtahir.alang.utils;

import com.nishtahir.alang.value.Value;
import com.nishtahir.alang.value.IntegerValue;
import com.nishtahir.alang.value.ListValue;

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
