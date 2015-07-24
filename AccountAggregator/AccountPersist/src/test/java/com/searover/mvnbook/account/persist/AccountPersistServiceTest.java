package com.searover.mvnbook.account.persist;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by searover on 7/23/15.
 */
public class AccountPersistServiceTest {

    private AccountPersistService service;

    @Before
    public void prepare() throws Exception{
        File persistDataFile = new File("target/test-classes/persist-data.xml");
        if(persistDataFile.exists()){
            persistDataFile.delete();
        }
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-persist.xml");
        service = (AccountPersistService) ctx.getBean("accountPersistService");

        Account account = new Account();
        account.setId("searover");
        account.setName("Searover Lu");
        account.setEmail("searover@gmail.com");
        account.setPassword("this_should_be_encrypted");
        account.setActivated(true);

        service.create(account);
    }

    @Test
    public void testReadAccount() throws Exception{
        Account account = service.read("searover");
        assertNotNull(account);
        assertEquals("searover", account.getId());
        assertEquals("Searover Lu", account.getName());
        assertEquals("searover@gmail.com",account.getEmail());
        assertEquals("this_should_be_encrypted", account.getPassword());
        assertTrue(account.isActivated());
    }
}
