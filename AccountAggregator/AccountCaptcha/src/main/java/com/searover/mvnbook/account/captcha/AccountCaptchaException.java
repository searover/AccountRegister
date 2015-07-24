package com.searover.mvnbook.account.captcha;

/**
 * Created by searover on 7/23/15.
 */
public class AccountCaptchaException extends Exception {

    public AccountCaptchaException() {
    }

    public AccountCaptchaException(String message) {
        super(message);
    }

    public AccountCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
