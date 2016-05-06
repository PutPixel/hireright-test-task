package com.github.putpixel.hireright;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgumentParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void less_then_three_args() throws Exception {
        String[] cmd = {};
        ArgumentParser.parse(cmd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalid_args() throws Exception {
        String[] cmd = { "https://www.google.co.uk;hts://www.google.co.uk", "", "-G" };
        ArgumentParser.parse(cmd);
    }

    @Test
    public void urls_split_correct() throws Exception {
        String[] cmd = { "https://www.google.co.uk;http://www.google.co.uk", "test", "-v", "-c" };
        Arguments args = ArgumentParser.parse(cmd);
        assertEquals("https://www.google.co.uk", args.getUrls().get(0));
        assertEquals("http://www.google.co.uk", args.getUrls().get(1));
    }

    @Test
    public void words_split_correct() throws Exception {
        String[] cmd = { "https://www.google.co.uk;http://www.google.co.uk", "test1;test2,test3", "-v", "-c" };
        Arguments args = ArgumentParser.parse(cmd);
        assertEquals("test1", args.getSearchWords().get(0));
        assertEquals("test2", args.getSearchWords().get(1));
        assertEquals("test3", args.getSearchWords().get(2));
    }

    @Test
    public void full_set_of_params() throws Exception {
        String[] cmd = { "https://www.google.co.uk", "test1", "-v", "-c", "-w", "-e"};
        Arguments args = ArgumentParser.parse(cmd);
        assertEquals("https://www.google.co.uk", args.getUrls().get(0));
        assertEquals("test1", args.getSearchWords().get(0));
        assertTrue(args.isVerbose());
        assertTrue(args.isCountPageCharacters());
        assertTrue(args.isCountWordOccurences());
        assertTrue(args.isExtractSentences());
    }
}
