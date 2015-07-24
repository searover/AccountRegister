package com.searover.mvnbook.template.method;

/**
 * Created by searover on 7/21/15.
 */
public abstract class AbstractBuild {

    public void build(){
        initialize();
        compile();
        test();
        packagee();
        integrationTest();
        deploy();
    }

    protected abstract void initialize();

    protected abstract void compile();

    protected abstract void test();

    protected abstract void packagee();

    protected abstract void integrationTest();

    protected abstract void deploy();
}
