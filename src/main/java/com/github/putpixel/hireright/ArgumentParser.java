package com.github.putpixel.hireright;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class ArgumentParser {

    private static final String WORD_DELIMITERS = "(,|;)";

    public static Arguments parse(String[] args) throws Exception {
        if (args.length <= 3) {
            throw new IllegalArgumentException("Incorrect arguments");
        }

        Arguments arguments = new Arguments();
        arguments.setUrls(extractURLs(args[0]));
        arguments.setSearchWords(extractSearchWords(args[1]));

        Iterator<String> iterator = Arrays.asList(args).listIterator(2);
        while (iterator.hasNext()) {
            String argument = iterator.next().toLowerCase().trim();
            switch (argument) {
            case "-v":
                arguments.setVerbose(true);
                break;
            case "-w":
                arguments.setCountWordOccurences(true);
                break;
            case "-c":
                arguments.setCountPageCharacters(true);
                break;
            case "-e":
                arguments.setExtractSentences(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument: '" + argument + "'");
            }
        }
        return arguments;
    }

    private static List<String> extractURLs(String argument) throws IOException {
        List<String> urls = new ArrayList<>();
        for (String resource : argument.split(WORD_DELIMITERS)) {
            if (isUrl(resource)) {
                urls.add(resource.trim());
            }
            else {
                throw new IllegalArgumentException("Not an URL: '" + resource + "', allowed delimiters are ',' and ';'");
            }
        }
        return urls;
    }

    private static List<String> extractSearchWords(String argument) {
        return Arrays.asList(argument.split(WORD_DELIMITERS)).stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static boolean isUrl(String resourcePath) {
        try {
            URI uri = new URI(resourcePath);
            return uri != null;
        } catch (URISyntaxException ex) {
        }
        return false;
    }
}
