package com.searover.mvnbook.account.service;

/**
 * Created by searover on 7/25/15.
 */
public interface AccountServices {

    String generateCaptchaKey() throws AccountServiceException;
}
