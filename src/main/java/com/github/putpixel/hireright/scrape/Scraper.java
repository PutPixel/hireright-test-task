package com.github.putpixel.hireright.scrape;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

    private static final String[] TAGS_TO_IGNORE_ARR =
            { "area", "base", "br", "col", "embed", "hr", "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr" };

    private static final Set<String> TAGS_TO_IGNORE = new HashSet<>(Arrays.asList(TAGS_TO_IGNORE_ARR));

    private static final Pattern TAG_START_OR_END = Pattern.compile("<(/?)(\\w+).*?>");

    private static class TagInfo {
        String original;
        String tag;
        int start;
        int end;
        boolean endTag;

        public TagInfo(Matcher matcher) {
            original = matcher.group();
            tag = matcher.group(2).trim().toLowerCase();
            endTag = !matcher.group(1).trim().isEmpty();
            start = matcher.start();
            end = matcher.end();
        }

        public boolean isEndTag() {
            return endTag;
        }

        @Override
        public String toString() {
            return original;
        }
    }

    public static List<String> scrape(StringBuilder html) {
        LinkedList<String> blocks = new LinkedList<>();
        Stack<TagInfo> tagsStack = new Stack<>();
        int startNext = 0;
        Matcher matcher = TAG_START_OR_END.matcher(html);
        while (matcher.find(startNext)) {
            TagInfo tag = new TagInfo(matcher);
            if (TAGS_TO_IGNORE.parallelStream().anyMatch(it -> tag.tag.toLowerCase().contains(it))) {
                html.delete(matcher.start(), matcher.end());
                startNext = matcher.start();
            }
            else if (tag.isEndTag()) {
                TagInfo startTag = tagsStack.pop();
                TagInfo endTag = tag;
                if (!startTag.tag.equals(endTag.tag)) {
                    throw new IllegalArgumentException("Incorrect html: Closing tag:" + endTag + ", opening tag: " + startTag);
                }
                blocks.add(String.valueOf(html.subSequence(startTag.end, endTag.start)));
                html.delete(startTag.start, endTag.end);
                startNext = startTag.start;
            }
            else {
                tagsStack.push(tag);
                startNext = tag.end;
            }
        }

        return blocks;
    }

}
