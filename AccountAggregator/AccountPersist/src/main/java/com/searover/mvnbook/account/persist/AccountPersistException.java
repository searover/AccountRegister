package com.searover.mvnbook.account.persist;

/**
 * Created by searover on 7/22/15.
 */
public class AccountPersistException extends Exception {

    private static final long serialVersionUID = -8334988626147289624L;

    public AccountPersistException(String message){
        super(message);
    }

    public AccountPersistException(String message, Throwable throwable){
        super(message,throwable);
    }
}
