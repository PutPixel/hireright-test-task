package com.github.putpixel.hireright;

public class Main {

    public static void main(String[] args) {
        try {
            Arguments arguments = ArgumentParser.parse(args);
            Application application = new Application(arguments);
            application.configure();
            application.launch();
        } catch (Throwable ex) {
            System.err.format("ERROR: %s\n\n", ex.getMessage());
            printUsage();
            ex.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println(
                "USAGE: java -jar scraper.jar <url(s)> <word(s)> <flags>\n\n" +
                        "Flags:\n" +
                        " -w - count number of provided word(s) occurrence on webpage\n" +
                        " -e - extract sentences’ which contain given words\n" +
                        " -c - count number of characters of each web page\n" +
                        " -v - verbosity flag\n\n");
    }
}
