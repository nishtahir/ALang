package com.nishtahir.value;

import com.nishtahir.evaluator.Operable;

/**
 * Abstract representation of a value in ALang
 * A value is simply a wrapper around an Object type
 * @param <T> Type of Object which this value will hold
 */
public abstract class Value<T> implements Comparable<Value<T>>, Operable{

    /**
     * List of types natively supported by ALang. Each type
     */
    public enum TYPE {
        INTEGER_VALUE(0), STRING_VALUE(1), BOOLEAN_VALUE(2), LIST_VALUE(3);

        private int value;

        TYPE(final int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    /**
     * Wrapped value
     */
    protected T value;

    /**
     * @return wrapped value contained
     */
    public abstract T getValue();

    /**
     * @return canonical name of the value
     */
    public abstract TYPE getType();

    /**
     * {@inheritDoc}
     *
     * @return representation of value contained. "nil" if empty
     */
    @Override
    public String toString() {
        return value != null ? value.toString() : "nil";
    }

    @Override
    public int compareTo(Value<T> tValue) {
        return 0;
    }

    @Override
    public Value add(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value subtract(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value multiply(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value divide(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value greaterThan(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value greaterThanEquals(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value lessThan(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value lessThanEquals(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value equals(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value not(Value value) {
        throw new UnsupportedOperationException();
    }

}
