package com.github.putpixel.hireright.task;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.putpixel.hireright.task.result.CharsOnPageResult;

public class CharactersCountingTaskTest {

    private CharactersCountingTask task;

    @Before
    public void before() {
        task = new CharactersCountingTask();
    }

    @Test
    public void test_One_Block() {
        List<String> list = new ArrayList<>();
        list.add("1234");
        CharsOnPageResult result = (CharsOnPageResult) task.execute(list);
        assertEquals(4, result.getChars());
    }

    @Test
    public void test_two_Block() {
        List<String> list = new ArrayList<>();
        list.add("1234");
        list.add("1234");
        CharsOnPageResult result = (CharsOnPageResult) task.execute(list);
        assertEquals(8, result.getChars());
    }

    @Test
    public void test_combine_results() {
        CharsOnPageResult res1 = new CharsOnPageResult();
        res1.add(3);
        CharsOnPageResult res2 = new CharsOnPageResult();
        res2.add(3);
        res1.combine(res2);
        assertEquals(6, res1.getChars());
    }

}
