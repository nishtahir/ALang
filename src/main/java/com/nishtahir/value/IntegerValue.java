package com.nishtahir.value;

/**
 * Wrapper class to contain {@link IntegerValue}
 */
public class IntegerValue extends Value<Integer>{

    public IntegerValue(Integer value) {
        this.value = value;
    }

    public IntegerValue(String value) {
        this.value = Integer.valueOf(value);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public TYPE getType() {
        return TYPE.INTEGER_VALUE;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(Value<Integer> o) {
        return value.compareTo(o.value);
    }

    @Override
    public Value add(Value value){
        switch (value.getType()){
            case INTEGER_VALUE:
                return new IntegerValue(this.getValue() + ((IntegerValue) value).getValue());
        }
        return super.add(value);
    }
}
