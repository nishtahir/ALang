package com.nishtahir.alang.exception;

import com.nishtahir.alang.ALang;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thrown when an operation attempts
 * to use an operand that is not supported.
 */
public class UnknownOperatorException extends ParseCancellationException {

    /**
     * Class logger. Initialized with name based on verbose setting.
     */
    private static final Logger LOGGER;

    static {
        if (ALang.isVerboseOutput()) {
            LOGGER = LoggerFactory.getLogger(UnknownOperatorException.class);
        } else {
            LOGGER = LoggerFactory.getLogger(
                    UnknownOperatorException.class.getSimpleName());
        }
    }

    /**
     * @param identifier offending operator
     * @param line line number in source where the operator was found
     */
    public UnknownOperatorException(final String identifier, final int line) {
        LOGGER.error("Error @ line:" + line
                + ". Illegal operator '" + identifier + "'.");
    }
}
