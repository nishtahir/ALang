package com.nishtahir.alang.exception;

import com.nishtahir.alang.ALang;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception to be thrown when a variable is attempted to be used
 * but has not been previously declared.
 */
public class UndeclaredVariableException extends ParseCancellationException {
    private static final Logger log;

    static {
        if (ALang.isVerboseOutput()) {
            log = LoggerFactory.getLogger(UndeclaredVariableException.class);
        } else {
            log = LoggerFactory.getLogger(UndeclaredVariableException.class.getSimpleName());
        }
    }

    /**
     *
     * @param identifier offending identifier that was not declared
     * @param line line number in source where the identifier was found
     */
    public UndeclaredVariableException(String identifier, int line) {
        log.error("Error @ line:" + line + ". Variable '" + identifier + "' has not been declared");
    }
}
