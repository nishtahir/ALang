package com.nishtahir.value;

/**
 *
 */
public class IntegerValue extends Value<Integer> {

    /**
     *
     * @param value
     */
    public IntegerValue(Integer value) {
        this.value = value;
    }

    /**
     *
     * @param value
     */
    public IntegerValue(String value) {
        this.value = Integer.valueOf(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
