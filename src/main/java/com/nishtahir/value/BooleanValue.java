package com.nishtahir.value;

/**
 * Created by Nish on 11/8/15.
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
