package com.nishtahir.alang.exception;

import com.nishtahir.alang.ALang;
import com.nishtahir.alang.value.Value;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nish on 12/5/15.
 */
public class UnsupportedOperationException extends ParseCancellationException {
    private static final Logger log;

    static {
        if (ALang.isVerboseOutput()) {
            log = LoggerFactory.getLogger(UnsupportedOperationException.class);
        } else {
            log = LoggerFactory.getLogger(UnsupportedOperationException.class.getSimpleName());
        }
    }

    public UnsupportedOperationException() {
        log.error("The operation is not supported for the type");
    }


    /**
     *
     * @param lhs
     * @param rhs
     * @param line
     */
    public UnsupportedOperationException(Value lhs, Value rhs, int line) {
        log.error("Error @ line:" + line + ". The type '" + lhs.getClass().getSimpleName()
                + "' can not be " + " OPERATION " + "to type " + rhs.getClass().getSimpleName());

    }

}
