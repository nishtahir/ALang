package com.nishtahir.evaluator;

import com.nishtahir.value.IntegerValue;
import com.nishtahir.value.StringValue;
import com.nishtahir.value.Value;

/**
 * Created by Nish on 11/7/15.
 */
public enum Operation {
    Add {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            lhs.setValue(lhs.getValue() + rhs.getValue());
            return lhs;
        }

        @Override
        public Value evaluate(IntegerValue lhs, StringValue rhs) {
            throw new UnsupportedOperationException("You can't add a String to an Integer");
        }

        @Override
        public Value evaluate(StringValue lhs, IntegerValue rhs) {
            lhs.setValue(lhs.getValue() + rhs.getValue());
            return lhs;
        }

        @Override
        public Value evaluate(StringValue lhs, StringValue rhs) {
            lhs.setValue(lhs.getValue() + rhs.getValue());
            return lhs;
        }
    }, Sub {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(IntegerValue lhs, StringValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, StringValue rhs) {
            return null;
        }
    }, Mult {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(IntegerValue lhs, StringValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, StringValue rhs) {
            return null;
        }
    }, Div {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(IntegerValue lhs, StringValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, IntegerValue rhs) {
            return null;
        }

        @Override
        public Value evaluate(StringValue lhs, StringValue rhs) {
            return null;
        }
    };

    public static Operation getValue(String op) {
        switch (op) {
            case "+":
                return Add;
            case "-":
                return Sub;
            case "*":
                return Mult;
            case "/":
                return Div;
            default:
                return null;
        }
    }

    public abstract Value evaluate(IntegerValue lhs, IntegerValue rhs);

    public abstract Value evaluate(IntegerValue lhs, StringValue rhs);

    public abstract Value evaluate(StringValue lhs, IntegerValue rhs);

    public abstract Value evaluate(StringValue lhs, StringValue rhs);

}
