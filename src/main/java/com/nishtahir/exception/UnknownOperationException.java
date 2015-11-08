package com.nishtahir.exception;

public class UnknownOperationException extends RuntimeException {

    public UnknownOperationException() {
    }

    public UnknownOperationException(String message) {
        super(message);
    }

    public UnknownOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownOperationException(Throwable cause) {
        super(cause);
    }

    public UnknownOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
