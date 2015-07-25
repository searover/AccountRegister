package com.searover.mvnbook.account.service;

/**
 * Created by searover on 7/25/15.
 */
public interface AccountService {

    String generateCaptchaKey() throws AccountServiceException;

    byte[] generateCaptchaImage (String captchaKey) throws AccountServiceException;

    void signUp(SignUpRequest signUpRequest) throws AccountServiceException;

    void activate(String activationId) throws AccountServiceException;

    void login(String id, String password) throws AccountServiceException;
}
