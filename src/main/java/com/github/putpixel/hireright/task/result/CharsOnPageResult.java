package com.github.putpixel.hireright.task.result;

import java.io.PrintStream;

public class CharsOnPageResult implements TaskResult {

    private long chars = 0L;

    @Override
    public void combine(TaskResult otherTask) {
        if (otherTask instanceof CharsOnPageResult) {
            CharsOnPageResult sentencesResultOther = (CharsOnPageResult) otherTask;
            chars += sentencesResultOther.chars;
        }
    }

    public void add(long chars) {
        this.chars += chars;
    }

    @Override
    public void printResult(PrintStream out) {
        out.println("Total characters: " + chars);
    }

    public long getChars() {
        return chars;
    }

}
