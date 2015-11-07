package com.nishtahir.value;

/**
 *
 * @param <T>
 */
public abstract class Value<T> {
    protected T value;

    /**
     *
     * @return
     */
    public abstract T getValue();


    @Override
    public String toString() {
        return value != null ? value.toString() : "nil";
    }
}
