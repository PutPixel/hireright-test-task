package com.github.putpixel.hireright.task.result;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SentencesResult implements TaskResult {

    private Map<String, Set<String>> wordToSentences = new HashMap<>();

    @Override
    public void combine(TaskResult otherTask) {
        if (otherTask instanceof SentencesResult) {
            SentencesResult sentencesResultOther = (SentencesResult) otherTask;
            sentencesResultOther.wordToSentences.forEach((key, value) -> put(key, value));
        }
    }

    public void put(String key, String value) {
        Set<String> set = get(key);
        set.add(value);
    }

    public void put(String key, Collection<String> values) {
        Set<String> set = get(key);
        set.addAll(values);
    }

    public Set<String> get(String key) {
        Set<String> set = wordToSentences.get(key);
        if (set == null) {
            set = new HashSet<>();
            wordToSentences.put(key, set);
        }
        return set;
    }

    @Override
    public void printResult(PrintStream out) {
        out.println("Found sentences:");
        wordToSentences.forEach((key, values) -> {
            out.println("***'" + key + "'");
            for (String value : values) {
                out.println("******: " + value);
            }
        });
    }

}
