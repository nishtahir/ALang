package com.nishtahir.alang.evaluator;

import com.nishtahir.alang.value.Value;

/**
 *
 *
 */
public interface Operable {

    /**
     * Invoked upon use of the + addition Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value add(Value value);

    /**
     * Invoked upon use of the - subtraction Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value subtract(Value value);

    /**
     * Invoked upon use of the * multiplication Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value multiply(Value value);

    /**
     * Invoked upon use of the / division Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value divide(Value value);

    /**
     * Invoked upon use of the > greater than Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value greaterThan(Value value);

    /**
     * Invoked upon use of the >= greater than and equals Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value greaterThanEquals(Value value);

    /**
     * Invoked upon use of the < less than Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value lessThan(Value value);

    /**
     * Invoked upon use of the <= less than and equals Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value lessThanEquals(Value value);

    /**
     * Invoked upon use of the = equals Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value equals(Value value);

    /**
     * Invoked upon use of the <=> not equals Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value not(Value value);

    /**
     * Invoked upon use of the max(x,y) Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value findMaximum(Value value);

    /**
     * Invoked upon use of the min(x,y) Operator
     *
     * @param value to be operated on
     * @return result
     */
    Value findMinimum(Value value);

}
