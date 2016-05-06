package com.github.putpixel.hireright;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ApplicationTest {

    private Arguments args;
    private Application application;

    public void before() {
        args = new Arguments();
        args.setProxyHost("http://zscaler.proxy.int.kn");
        args.setProxyPort("80");
        application = new Application(args);
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }

}
