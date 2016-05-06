package com.github.putpixel.hireright.task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.putpixel.hireright.Arguments;
import com.github.putpixel.hireright.task.result.TaskResult;
import com.github.putpixel.hireright.task.result.WordCounterResult;

public class WordCounterTask extends Task {

    private Arguments args;

    public WordCounterTask(Arguments args) {
        this.args = args;
    }

    @Override
    public TaskResult execute(List<String> blocks) {
        WordCounterResult result = new WordCounterResult();
        args.getSearchWords().forEach(word -> result.add(word, 0));
        blocks.forEach(content -> result.combine(countWords(content)));
        return result;
    }

    private WordCounterResult countWords(String content) {
        WordCounterResult result = new WordCounterResult();
        args.getSearchWords().forEach(word -> result.add(word, count(content, word)));
        return result;
    }

    private int count(String content, String word) {
        int i = 0;
        Pattern p = Pattern.compile(word);
        Matcher m = p.matcher(content);
        while (m.find()) {
            i++;
        }
        return i;
    }

    @Override
    public TaskResult getNewResult() {
        return new WordCounterResult();
    }

	@Override
	public String getTaskName() {
		return "Word(s) occurrence";
	}

}
