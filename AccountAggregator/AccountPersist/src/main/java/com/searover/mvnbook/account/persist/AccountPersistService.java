package com.searover.mvnbook.account.persist;

/**
 * Created by searover on 7/22/15.
 */
public interface AccountPersistService {

    Account create(Account account) throws AccountPersistException;

    Account read(String id) throws AccountPersistException;

    Account update(Account account) throws AccountPersistException;

    void delete(String id) throws AccountPersistException;

}
