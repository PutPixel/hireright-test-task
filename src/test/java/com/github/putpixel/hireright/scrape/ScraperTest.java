package com.github.putpixel.hireright.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ScraperTest {

    @Test
    public void simple_page() {
        String html = "<HTML>\r\n" +
                "<HEAD>\r\n" +
                "<TITLE>webpage1</TITLE>\r\n" +
                "</HEAD>\r\n" +
                "<BODY BGCOLOR=\"FFFFFf\" LINK=\"006666\" ALINK=\"8B4513\" VLINK=\"006666\">\r\n" +
                "<BR>\r\n" +
                "<DIV ALIGN=\"center\"><H1>STARTING . . . </H1></DIV>\r\n" +
                "</BODY>\r\n" +
                "</HTML>";
        List<String> result = Scraper.scrape(new StringBuilder(html));
        assertNotNull(result);
        assertEquals("webpage1", result.get(0));
    }

    @Test
    public void invali_html_more_open_tag() {
        String html = "<HTML>\r\n" +
                "<HEAD>\r\n" +
                "<TITLE>webpage1</TITLE>\r\n" +
                "</HEAD>\r\n" +
                "<BODY BGCOLOR=\"FFFFFf\" LINK=\"006666\" ALINK=\"8B4513\" VLINK=\"006666\">\r\n" +
                "<BR>\r\n" +
                "<DIV ALIGN=\"center\"><H1>STARTING . . . </DIV>\r\n" +
                "</BODY>\r\n" +
                "</HTML>";
        List<String> result = Scraper.scrape(new StringBuilder(html));
        assertEquals(html, result.get(0));
    }

    @Test
    public void invalid_html_more_close_tag() {
        String html = "<HTML>\r\n" +
                "\r\n" +
                "<TITLE>webpage1</TITLE>\r\n" +
                "</HEAD>\r\n" +
                "<BODY BGCOLOR=\"FFFFFf\" LINK=\"006666\" ALINK=\"8B4513\" VLINK=\"006666\">\r\n" +
                "<BR>\r\n" +
                "<DIV ALIGN=\"center\"><H1>STARTING . . . </H1></DIV>\r\n" +
                "</BODY>\r\n" +
                "</HTML>";
        List<String> result = Scraper.scrape(new StringBuilder(html));
        assertEquals(html, result.get(0));
    }

    @Test
    public void invalid_html_inside_tag_deleted_problem() {
        String html = "<HTML>\r\n" +
                "<HEAD>\r\n" +
                "<TITLE>webpage1</TITLE>\r\n" +
                "</HEAD>\r\n" +
                "<BODY BGCOLOR=\"FFFFFf\" LINK=\"006666\" ALINK=\"8B4513\" VLINK=\"006666\">\r\n" +
                "<BR>\r\n" +
                "<DIV ALIGN=\"center\"><H1>STARTING . . . </H1></DIV>\r\n" +
                "<DIV ALIGN=\"center\"><H1>STARTING . . . </DIV>\r\n" +
                "</BODY>\r\n" +
                "</HTML>";
        List<String> result = Scraper.scrape(new StringBuilder(html));
        assertNotNull(result);
        assertEquals(html, result.get(0));
    }
}
