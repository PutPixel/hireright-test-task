package com.github.putpixel.hireright;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ApplicationLogger {

    private final LocalDateTime startApplication;

    private LocalDateTime startRetrievingContent;

    private LocalDateTime startProcess;

    public static ApplicationLogger create(Arguments args) {
        if (args.isVerbose()) {
            return new ApplicationLogger();
        }
        else {
            return new ApplicationLogger() {
                private void logInfo() {
                    // Does nothing as no verbose mode
                }
            };
        }
    }

    private ApplicationLogger() {
        startApplication = LocalDateTime.now();
    }

    private void logInfo(String format, Object... args) {
        System.out.format(format, args);
    }

    public void startApplication() {
        logInfo("Start program at: %s\n", startApplication.toString());
    }

    public void startRetrievingContent(String url) {
        startRetrievingContent = LocalDateTime.now();
        logInfo("Start retrieving content from \"%s\" at: %s\n",
                url, startRetrievingContent);
    }

    public void endRetrievingContent() {
        LocalDateTime end = LocalDateTime.now();
        logInfo("End retrieving content at %s\nTotal time: %d milliseconds\n\n",
                end.toString(), ChronoUnit.MILLIS.between(startRetrievingContent, end));
    }

    public void startTask() {
        startProcess = LocalDateTime.now();
    }

    public void endTask() {
        LocalDateTime end = LocalDateTime.now();
        logInfo("Task total time: %d milliseconds\n\n",
                ChronoUnit.MILLIS.between(startProcess, end));
    }

    public void endApplication() {
        LocalDateTime end = LocalDateTime.now();
        logInfo("End program at: %s\nTotal time: %d milliseconds\n\n",
                end.toString(), ChronoUnit.MILLIS.between(startApplication, end));
    }
}