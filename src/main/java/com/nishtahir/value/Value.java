package com.nishtahir.value;

/**
 * Abstract representation of a value in ALang
 * A value is simply a wrapper around an Object type
 * @param <T> Type of Object which this value will hold
 */
public abstract class Value<T> implements Comparable<Value<T>>{
    protected T value;

    /**
     * @return wrapped value contained
     */
    public abstract T getValue();

    /**
     * @return canonical name of the value
     */
    public String getType(){
        return this.getClass().getCanonicalName();
    }

    /**
     * {@inheritDoc}
     *
     * @return representation of value contained. "nil" if empty
     */
    @Override
    public String toString() {
        return value != null ? value.toString() : "nil";
    }

}
