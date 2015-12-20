package com.nishtahir.alang.exception;

import com.nishtahir.alang.ALang;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thrown when a variable that has not been
 * previously declared is attempted to be used.
 */
public class UndeclaredVariableException extends ParseCancellationException {
    /**
     *
     */
    private static final Logger LOGGER;

    static {
        if (ALang.isVerboseOutput()) {
            LOGGER = LoggerFactory.getLogger(UndeclaredVariableException.class);
        } else {
            LOGGER = LoggerFactory.getLogger(
                    UndeclaredVariableException.class.getSimpleName());
        }
    }

    /**
     * @param identifier offending identifier that was not declared
     * @param line       line number in source where the identifier was found
     */
    public UndeclaredVariableException(final String identifier,
                                       final int line) {
        LOGGER.error("Error @ line:" + line + ". Variable '"
                + identifier + "' has not been declared");
    }
}
