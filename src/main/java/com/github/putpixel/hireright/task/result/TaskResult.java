package com.github.putpixel.hireright.task.result;

import java.io.PrintStream;

public interface TaskResult {

    void combine(TaskResult otherTask);

    void printResult(PrintStream out);

}
