package com.nishtahir.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Wrapper class to contain {@link List}
 * The contained list can hold other {@link Value}
 *
 * <b>Indexes in ALang start from 1</b>
 */
public class ListValue extends Value<List<Value>> {

    public ListValue() {
        value = new ArrayList<>();
    }

    @Override
    public List<Value> getValue() {
        return value;
    }

    @Override
    public TYPE getType() {
        return TYPE.LIST_VALUE;
    }

    @Override
    public int compareTo(Value<List<Value>> o) {
        if (value.size() > o.value.size()) {
            return 1;
        } else if (value.size() < o.value.size()) {
            return -1;
        }
        return 0;
    }

    /**
     *
     * @param index index in list to return
     * @return value contained at the specified index
     */
    public Value getValueAtIndex(IntegerValue index){
        return this.value.get(index.getValue() - 1);
    }

    /**
     *
     * @param index index to set the given value
     * @param value value to set at the given index
     * @return value that was set at the given index
     */
    public Value setValueAtIndex(IntegerValue index, Value value){
        return this.value.set(index.getValue() - 1, value);
    }

    @Override
    public String toString() {
        return Arrays.toString(value.toArray());
    }
}
