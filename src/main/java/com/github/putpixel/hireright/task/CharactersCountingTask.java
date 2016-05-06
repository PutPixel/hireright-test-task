package com.github.putpixel.hireright.task;

import java.util.List;

import com.github.putpixel.hireright.task.result.CharsOnPageResult;
import com.github.putpixel.hireright.task.result.TaskResult;

public class CharactersCountingTask extends Task {

    @Override
    public TaskResult getNewResult() {
        return new CharsOnPageResult();
    }

    @Override
    public TaskResult execute(List<String> blocks) {
        CharsOnPageResult result = new CharsOnPageResult();
        blocks.forEach(it -> result.add(it.length()));
        return result;
    }

	@Override
	public String getTaskName() {
		return "Characters count on page";
	}

}
