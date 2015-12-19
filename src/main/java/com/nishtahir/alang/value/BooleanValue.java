package com.nishtahir.alang.value;

/**
 * Wrapper class to contain {@link Boolean}
 */
public class BooleanValue extends Value<Boolean> {

    public BooleanValue() {

    }

    public BooleanValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public TYPE getType() {
        return TYPE.BOOLEAN_VALUE;
    }

    @Override
    public int compareTo(Value<Boolean> o) {
        return this.value.compareTo(o.value);
    }
}
