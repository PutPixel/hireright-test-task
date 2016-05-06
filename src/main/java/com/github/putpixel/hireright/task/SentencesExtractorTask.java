package com.github.putpixel.hireright.task;

import java.text.BreakIterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.github.putpixel.hireright.Arguments;
import com.github.putpixel.hireright.task.result.SentencesResult;
import com.github.putpixel.hireright.task.result.TaskResult;

public class SentencesExtractorTask extends Task {

    private Arguments args;

    public SentencesExtractorTask(Arguments args) {
        this.args = args;
    }

    @Override
    public TaskResult execute(List<String> blocks) {
        SentencesResult result = new SentencesResult();
        blocks.forEach(content -> result.combine(extractSentences(content)));
        args.getSearchWords().forEach(word -> {
            if (result.get(word).isEmpty()) {
                result.put(word, "No sentences found");
            }
        });
        return result;
    }

    private SentencesResult extractSentences(String content) {
        SentencesResult result = new SentencesResult();
        args.getSearchWords().forEach(word -> result.put(word, extractAll(content, word)));
        return result;
    }

    private Collection<String> extractAll(String content, String word) {
        HashSet<String> result = new HashSet<>();
        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(content);
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
            String sentence = content.substring(index, bi.current());
            index = bi.current();
            if (sentence.contains(word)) {
                result.add(sentence.trim());
            }
        }
        return result;
    }

    @Override
    public TaskResult getNewResult() {
        return new SentencesResult();
    }

	@Override
	public String getTaskName() {
		return "Sentences with given words";
	}

}
