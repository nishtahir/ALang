package com.nishtahir.value;

/**
 * Wrapper class to contain {@link Boolean}
 */
public class BooleanValue extends Value<Boolean> {

    @Override
    public Boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public int compareTo(Value<Boolean> o) {
        return this.value.compareTo(o.value);
    }
}
