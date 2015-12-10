package com.nishtahir.evaluator;

import com.nishtahir.value.BooleanValue;
import com.nishtahir.value.IntegerValue;
import com.nishtahir.value.StringValue;
import com.nishtahir.value.Value;

import java.util.Objects;

/**
 * Created by Nish on 11/7/15.
 */
public enum Operation {
    Greater {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            BooleanValue value = new BooleanValue();
            if(lhs.getValue() > rhs.getValue()){
                value.setValue(true);
            } else {
                value.setValue(false);
            }
            return value;
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
    }, Less {
        @Override
        public Value evaluate(IntegerValue lhs, IntegerValue rhs) {
            BooleanValue value = new BooleanValue();
            if(lhs.getValue() < rhs.getValue()){
                value.setValue(true);
            } else {
                value.setValue(false);
            }
            return value;
        }

        @Override
        public Value evaluate(IntegerValue lhs, StringValue rhs) {
            throw new UnsupportedOperationException("You can't compare a number to a string");
        }

        @Override
        public Value evaluate(StringValue lhs, IntegerValue rhs) {
            throw new UnsupportedOperationException("You can't compare a string to a number");
        }

        @Override
        public Value evaluate(StringValue lhs, StringValue rhs) {
            BooleanValue value = new BooleanValue();
            if(lhs.getValue().compareTo(rhs.getValue()) > 0){
                value.setValue(true);
            } else {
                value.setValue(false);
            }
            return value;
        }
    };

    public abstract Value evaluate(IntegerValue lhs, IntegerValue rhs);

    public abstract Value evaluate(IntegerValue lhs, StringValue rhs);

    public abstract Value evaluate(StringValue lhs, IntegerValue rhs);

    public abstract Value evaluate(StringValue lhs, StringValue rhs);

}
