package com.searover.mvnbook.account.email;

import com.searover.mvnbook.account.email.exception.AccountEmailException;

/**
 * Created by searover on 7/20/15.
 */
public interface AccountEmailService {

    void sendMail(String to, String subject, String htmlText)
        throws AccountEmailException;
}
