package com.searover.mvnbook.account.email;

import static junit.framework.Assert.assertEquals;

import javax.mail.Message;

import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icegreen.greenmail.util.GreenMail;

/**
 * Created by searover on 7/21/15.
 */
public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception{
        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.setUser("bai1guang@sina.com","123456");

        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService =
                (AccountEmailService)ctx.getBean("accountEmailService");

        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendMail("bai2guang@test.com", subject, htmlText);

        greenMail.waitForIncomingEmail(2000, 1);

        Message[] msgs = greenMail.getReceivedMessages();
        System.out.println(msgs[0].getContent());
        assertEquals(1, msgs.length);
        assertEquals(subject, msgs[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception{
        greenMail.stop();
    }

}
