package com.nishtahir.evaluator;

import com.nishtahir.value.IntegerValue;
import com.nishtahir.value.StringValue;
import com.nishtahir.value.Value;

/**
 *
 */
public class ValueEvaluator {

    /**
     * @param lhs
     * @param rhs
     * @param operation
     * @return
     */
    public static Value evaluate(Value lhs, Value rhs, Operation operation) {
        if (operation == null) {
            return null;
        }

        if (lhs instanceof IntegerValue) {
            if (rhs instanceof IntegerValue) {
                return operation.evaluate((IntegerValue) lhs, (IntegerValue) rhs);
            } else if (rhs instanceof StringValue) {
                return operation.evaluate((IntegerValue) lhs, (StringValue) rhs);
            }
        } else if (lhs instanceof StringValue) {
            if (rhs instanceof IntegerValue) {
                return operation.evaluate((StringValue) lhs, (IntegerValue) rhs);
            } else if (rhs instanceof StringValue) {
                return operation.evaluate((StringValue) lhs, (StringValue) rhs);
            }
        }

        return null;
    }
}
