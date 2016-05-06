package com.github.putpixel.hireright;

import org.junit.Test;

public class ApplicationITest {

    private Arguments args;

    private Application application;

    @Test
    public void yaru() {
        String[] cmd = { "https://simplesite.com", "Отзывы", "-v", "-c", "-w", "-e", "-h", "http://zscaler.proxy.int.kn", "-p", "80" };
        Main.main(cmd);
    }

}
