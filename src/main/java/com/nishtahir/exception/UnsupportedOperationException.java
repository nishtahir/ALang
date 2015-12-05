package com.nishtahir.exception;

import com.nishtahir.ALangApplication;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nish on 12/5/15.
 */
public class UnsupportedOperationException extends ParseCancellationException {
    private static final Logger log;

    static {
        if (ALangApplication.MODE_VERBOSE) {
            log = LoggerFactory.getLogger(UnsupportedOperationException.class);
        } else {
            log = LoggerFactory.getLogger(UnsupportedOperationException.class.getSimpleName());
        }
    }

    public UnsupportedOperationException() {
        log.error("Unsupported Operation");
    }

    /**
     *
     * @param identifier
     * @param line
     */
    public UnsupportedOperationException(String identifier, int line) {
        log.error("Error @ line:" + line);
    }

}
