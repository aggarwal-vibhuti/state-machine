package com.fsm.exceptions;

public class InvalidTransitionException extends RuntimeException {

    public InvalidTransitionException(String errorMessage) {
        super(errorMessage);
    }
}
