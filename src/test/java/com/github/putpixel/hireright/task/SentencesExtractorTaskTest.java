package com.github.putpixel.hireright.task;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.putpixel.hireright.Arguments;
import com.github.putpixel.hireright.task.result.SentencesResult;

public class SentencesExtractorTaskTest {

    private static final String WORD1 = "word1";
    private static final String WORD2 = "word2";

    private SentencesExtractorTask task;

    @Before
    public void before() {
        Arguments args = new Arguments();
        List<String> list = new ArrayList<>();
        list.add(WORD1);
        list.add(WORD2);
        args.setSearchWords(list);
        task = new SentencesExtractorTask(args);
    }

    @Test
    public void no_blocks() {
        SentencesResult result = (SentencesResult) task.execute(new ArrayList<>());
        assertThat(result.get(WORD1), containsInAnyOrder("No sentences found"));
        assertThat(result.get(WORD2), containsInAnyOrder("No sentences found"));
    }

    @Test
    public void one_block() {
        List<String> blocks = new ArrayList<>();
        blocks.add("testst sf " + WORD1 + " safd " + WORD2 + " safd");
        SentencesResult result = (SentencesResult) task.execute(blocks);

        assertThat(result.get(WORD1), containsInAnyOrder("testst sf " + WORD1 + " safd " + WORD2 + " safd"));
        assertThat(result.get(WORD2), containsInAnyOrder("testst sf " + WORD1 + " safd " + WORD2 + " safd"));
    }

    @Test
    public void two_blocks() {
        List<String> blocks = new ArrayList<>();
        blocks.add("a " + WORD1 + ". Safd " + WORD2 + " safd");
        blocks.add("b" + WORD1 + " Safd " + WORD2 + " safd");
        SentencesResult result = (SentencesResult) task.execute(blocks);

        assertThat(result.get(WORD1), containsInAnyOrder("a " + WORD1 + ".", "b" + WORD1 + " Safd " + WORD2 + " safd"));
        assertThat(result.get(WORD2), containsInAnyOrder("Safd " + WORD2 + " safd", "b" + WORD1 + " Safd " + WORD2 + " safd"));
    }

}
