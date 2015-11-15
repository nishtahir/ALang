package com.nishtahir.value;

/**
 * Wrapper class to contain {@link IntegerValue}
 */
public class IntegerValue extends Value<Integer> {

    public IntegerValue(Integer value) {
        this.value = value;
    }

    public IntegerValue(String value) {
        this.value = Integer.valueOf(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Value<Integer> o) {
        int result = this.value.compareTo(o.value);
        return result;
    }
}
