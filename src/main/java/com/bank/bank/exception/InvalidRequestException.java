package com.bank.bank.exception;

public class InvalidRequestException extends Exception {

    private static final long serialVersionUID = 1L;

        public InvalidRequestException(String msg) {
            super(msg);
        }

}
