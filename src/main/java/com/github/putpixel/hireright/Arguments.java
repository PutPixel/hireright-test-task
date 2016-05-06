package com.github.putpixel.hireright;

import java.io.PrintStream;
import java.util.List;

public final class Arguments {

    private PrintStream output = System.out;

    private List<String> urls;

    private List<String> searchWords;

    private boolean countWordOccurences;

    private boolean countPageCharacters;

    private boolean extractSentences;

    private boolean verbose;

    private String proxyHost;

    private String proxyPort;

    public PrintStream getOutput() {
        return output;
    }

    public void setOutput(PrintStream output) {
        this.output = output;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(List<String> searchWords) {
        this.searchWords = searchWords;
    }

    public boolean isCountWordOccurences() {
        return countWordOccurences;
    }

    public void setCountWordOccurences(boolean countWordOccurences) {
        this.countWordOccurences = countWordOccurences;
    }

    public boolean isCountPageCharacters() {
        return countPageCharacters;
    }

    public void setCountPageCharacters(boolean countPageCharacters) {
        this.countPageCharacters = countPageCharacters;
    }

    public boolean isExtractSentences() {
        return extractSentences;
    }

    public void setExtractSentences(boolean extractSentences) {
        this.extractSentences = extractSentences;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public boolean hasProxy() {
        return proxyHost != null;
    }

}
