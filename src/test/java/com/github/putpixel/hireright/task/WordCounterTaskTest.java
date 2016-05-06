package com.github.putpixel.hireright.task;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.putpixel.hireright.Arguments;
import com.github.putpixel.hireright.task.result.WordCounterResult;

public class WordCounterTaskTest {

    private static final String WORD1 = "word1";
    private static final String WORD2 = "word2";

    private WordCounterTask task;

    @Before
    public void before() {
        Arguments args = new Arguments();
        List<String> list = new ArrayList<>();
        list.add(WORD1);
        list.add(WORD2);
        args.setSearchWords(list);
        task = new WordCounterTask(args);
    }

    @Test
    public void no_blocks() {
        WordCounterResult result = (WordCounterResult) task.execute(new ArrayList<>());
        assertEquals(0, result.get(WORD1));
        assertEquals(0, result.get(WORD2));
    }

    @Test
    public void one_block() {
        List<String> blocks = new ArrayList<>();
        blocks.add("testst sf " + WORD1 + " safd " + WORD2 + " safd");
        WordCounterResult result = (WordCounterResult) task.execute(blocks);
        assertEquals(1, result.get(WORD1));
        assertEquals(1, result.get(WORD2));
    }

    @Test
    public void two_blocks() {
        List<String> blocks = new ArrayList<>();
        blocks.add("testst sf " + WORD1 + " safd " + WORD2 + " safd");
        blocks.add("testst sf " + WORD1 + " safd  safd");
        WordCounterResult result = (WordCounterResult) task.execute(blocks);
        assertEquals(2, result.get(WORD1));
        assertEquals(1, result.get(WORD2));
    }

    @Test
    public void three_blocks() {
        List<String> blocks = new ArrayList<>();
        blocks.add("testst sf " + WORD1 + " safd " + WORD2 + " safd");
        blocks.add("testst sf " + WORD1 + " safd  safd");
        blocks.add("testst sf " + WORD1 + " safd  safd");
        WordCounterResult result = (WordCounterResult) task.execute(blocks);
        assertEquals(3, result.get(WORD1));
        assertEquals(1, result.get(WORD2));
    }

}
