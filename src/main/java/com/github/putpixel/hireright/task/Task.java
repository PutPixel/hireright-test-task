package com.github.putpixel.hireright.task;

import java.util.List;

import com.github.putpixel.hireright.task.result.TaskResult;

public abstract class Task {

	public abstract String getTaskName();

    public abstract TaskResult execute(List<String> blocks);

    public abstract TaskResult getNewResult();
}
