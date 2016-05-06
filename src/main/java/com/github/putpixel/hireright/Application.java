package com.github.putpixel.hireright;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.putpixel.hireright.scrape.Scraper;
import com.github.putpixel.hireright.task.CharactersCountingTask;
import com.github.putpixel.hireright.task.SentencesExtractorTask;
import com.github.putpixel.hireright.task.Task;
import com.github.putpixel.hireright.task.WordCounterTask;
import com.github.putpixel.hireright.task.result.TaskResult;

public class Application {

    private final Arguments args;

    private final List<Task> tasks = new ArrayList<>();

    public Application(Arguments arguments) {
        this.args = arguments;
    }

    public void configure() {
        if (args.isCountPageCharacters()) {
            addTask(new CharactersCountingTask());
        }
        if (args.isCountWordOccurences()) {
            addTask(new WordCounterTask(args));
        }
        if (args.isExtractSentences()) {
            addTask(new SentencesExtractorTask(args));
        }
    }

    private void addTask(Task workProcess) {
        tasks.add(workProcess);
    }

    public void launch() throws Exception {
        ApplicationLogger log = ApplicationLogger.create(args);
        log.startApplication();
        List<TaskResult> totalResults = tasks.stream().map(it -> it.getNewResult()).collect(Collectors.toList());
        for (String urlString : args.getUrls()) {
            log.startRetrievingContent(urlString);
            StringBuilder html = retrieveContent(urlString);
            List<String> blocks = Scraper.scrape(new StringBuilder(html));
            log.endRetrievingContent();
            tasks.forEach((workProcess) -> {
                log.startTask(workProcess);
                TaskResult result = workProcess.execute(blocks);
                result.printResult(args.getOutput());
                totalResults.forEach(it -> it.combine(result));
                log.endTask(workProcess);
                args.getOutput().println();
            });
        }
        args.getOutput().println();
        args.getOutput().println();
		args.getOutput().println("***Totals***");
		totalResults.forEach(it -> {
			it.printResult(args.getOutput());
			args.getOutput().println();
		});
		log.endApplication();
    }

	private StringBuilder retrieveContent(String urlString) throws IOException, URISyntaxException {
		URL website = new URL(urlString);
		int trys = 3;
		while (trys > 0) {
			try {
				trys--;
				URLConnection connection = website.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				StringBuilder response = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					response.append(inputLine);

				in.close();
				return response;
			} catch (IOException e) {
				if (trys == 0) {
					throw e;
				}
			}
		}

		throw new RuntimeException("Should not be here");
	}
}
