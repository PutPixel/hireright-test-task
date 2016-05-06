package com.github.putpixel.hireright.task.result;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class WordCounterResult implements TaskResult {

    private Map<String, Integer> wordToCounter = new HashMap<>();

    @Override
    public void combine(TaskResult otherTask) {
        if (otherTask instanceof WordCounterResult) {
            WordCounterResult sentencesResultOther = (WordCounterResult) otherTask;
            sentencesResultOther.wordToCounter.forEach((key, value) -> add(key, value));
        }
    }

    public void add(String key, int value) {
        int valueStored = value(key);
        wordToCounter.put(key, valueStored + value);
    }

    public int get(String word) {
        return wordToCounter.get(word);
    }

    private int value(String key) {
        Integer number = wordToCounter.get(key);
        if (number == null) {
            return 0;
        }
        return number;
    }

    @Override
    public void printResult(PrintStream out) {
        out.println("Words count on page:");
        wordToCounter.forEach((key, value) -> {
            out.println("***" + key + " value: " + value);
        });
    }

}
