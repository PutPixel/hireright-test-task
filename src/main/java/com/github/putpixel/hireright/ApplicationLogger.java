package com.github.putpixel.hireright;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.github.putpixel.hireright.task.Task;

public abstract class ApplicationLogger {

	private final LocalDateTime startApplication;

	private LocalDateTime startRetrievingContent;

	private LocalDateTime startProcess;

	public static ApplicationLogger create(Arguments arguments) {
		if (arguments.isVerbose()) {
			return new ApplicationLogger(){
				void logInfo(String format, Object... args) {
					arguments.getOutput().format(format, args);
				}
			};
		}
		else {
			return new ApplicationLogger() {
				void logInfo(String format, Object... args) {
					// Does nothing as no verbose mode
				}
			};
		}
	}

	abstract void logInfo(String format, Object... args) ;

	private ApplicationLogger() {
		startApplication = LocalDateTime.now();
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