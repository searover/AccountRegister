package com.searover.mvnbook.account.captcha;

import java.util.List;

/**
 * Created by searover on 7/23/15.
 */
public interface AccountCaptchaService {

    String generateCaptchaKey() throws AccountCaptchaException;

    byte[] generateCaptchaImage(String captchaKey) throws AccountCaptchaException;

    boolean validateCaptcha(String captchaKey, String captchaValue) throws AccountCaptchaException;

    List<String> getPreDefinedTexts();

    void setPreDefinedTexts(List<String> preDefinedTexts);
}
