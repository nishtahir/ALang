package com.nishtahir.alang.value;

/**
 * Wrapper class to contain {@link IntegerValue}
 */
public class IntegerValue extends Value<Integer> {

    /**
     * Default value is 0 for Integer values
     */
    public IntegerValue() {
        this.value = 0;
    }

    /**
     * @param value
     */
    public IntegerValue(Integer value) {
        this.value = value;
    }

    /**
     * @param value
     */
    public IntegerValue(int value) {
        this.value = value;
    }

    /**
     * @param value String representation of a number
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

    @Override
    public TYPE getType() {
        return TYPE.INTEGER_VALUE;
    }

    @Override
    public int compareTo(Value<Integer> o) {
        if (this == o) {
            return 0;
        } else if (o == null) {
            return 1;
        }

        return value.compareTo(o.value);
    }

    @Override
    public Value add(Value value) {
        switch (value.getType()) {
            case INTEGER_VALUE:
                return new IntegerValue(this.getValue() + ((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Value subtract(Value value) {
        switch (value.getType()) {
            case INTEGER_VALUE:
                return new IntegerValue(this.getValue() - ((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Value multiply(Value value) {
        switch (value.getType()) {
            case INTEGER_VALUE:
                return new IntegerValue(this.getValue() * ((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Value findMaximum(Value value) {
        switch (value.getType()) {
            case INTEGER_VALUE:
                if (this.getValue() > ((IntegerValue) value).getValue())
                    return new IntegerValue(this.getValue());
                else
                    return new IntegerValue(((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();

        }

    }
    
    @Override


    public Value findMinimum(Value value) {
        switch (value.getType()) {
            case INTEGER_VALUE:
                if (this.getValue() < ((IntegerValue) value).getValue())
                    return new IntegerValue(this.getValue());
                else
                    return new IntegerValue(((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();

        }

    }
}
