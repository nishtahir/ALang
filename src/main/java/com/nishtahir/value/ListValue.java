package com.nishtahir.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nish on 11/8/15.
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
    public int compareTo(Value<List<Value>> o) {
        if (value.size() > o.value.size()) {
            return 1;
        } else if (value.size() < o.value.size()) {
            return -1;
        }
        return 0;
    }

    public Value getAtIndex(IntegerValue index){
        return this.value.get(index.getValue() - 1);
    }
    public Value setAtIndex(IntegerValue index, Value value){
        return this.value.set(index.getValue() - 1, value);
    }

    @Override
    public String toString() {
        return Arrays.toString(value.toArray());
    }
}
