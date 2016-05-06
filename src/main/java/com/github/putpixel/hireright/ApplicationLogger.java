package com.github.putpixel.hireright;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.github.putpixel.hireright.task.Task;

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
		logInfo("Start retrieving content from '%s' at: %s\n",
				url, startRetrievingContent);
	}

	public void endRetrievingContent() {
		LocalDateTime end = LocalDateTime.now();
		logInfo("End retrieving content at '%s'. Total time: %d milliseconds\n\n",
				end.toString(), ChronoUnit.MILLIS.between(startRetrievingContent, end));
	}

	public void startTask(Task task) {
		startProcess = LocalDateTime.now();
		logInfo("Start task '%s' at: %s\n",
				task.getTaskName(), startProcess.toString());
	}

	public void endTask(Task task) {
		LocalDateTime end = LocalDateTime.now();
		logInfo("Task '%s' finished. Total time: %d milliseconds\n\n",
				task.getTaskName(), ChronoUnit.MILLIS.between(startProcess, end));
	}

	public void endApplication() {
		LocalDateTime end = LocalDateTime.now();
		logInfo("End program at: %s. Total time: %d milliseconds\n\n",
				end.toString(), ChronoUnit.MILLIS.between(startApplication, end));
	}
}