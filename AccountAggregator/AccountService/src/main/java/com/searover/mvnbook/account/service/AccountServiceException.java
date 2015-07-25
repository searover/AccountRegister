package com.searover.mvnbook.account.service;

/**
 * Created by searover on 7/25/15.
 */
public class AccountServiceException extends Exception {
    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountServiceException(String message) {

        super(message);
    }

    public AccountServiceException() {
    }
}
