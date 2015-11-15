package com.nishtahir.exception;

import com.nishtahir.ALangApplication;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception to be thrown when an operation attempts to use an operand that is not
 * supported.
 */
public class UnknownOperatorException extends ParseCancellationException {
    private static final Logger log;

    static {
        if (ALangApplication.MODE_VERBOSE) {
            log = LoggerFactory.getLogger(UnknownOperatorException.class);
        } else {
            log = LoggerFactory.getLogger(UnknownOperatorException.class.getSimpleName());
        }
    }

    /**
     * @param identifier offending operator
     * @param line line number in source where the operator was found
     */
    public UnknownOperatorException(String identifier, int line) {
        log.error("Error @ line:" + line + ". Illegal operator '" + identifier + "'.");
    }
}
