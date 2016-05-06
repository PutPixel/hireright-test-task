package com.github.putpixel.hireright;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.putpixel.hireright.scrape.ScraperTest;
import com.github.putpixel.hireright.task.CharactersCountingTaskTest;
import com.github.putpixel.hireright.task.SentencesExtractorTaskTest;
import com.github.putpixel.hireright.task.WordCounterTaskTest;

@RunWith(Suite.class)
@SuiteClasses({ ArgumentParserTest.class, ScraperTest.class, CharactersCountingTaskTest.class,
		SentencesExtractorTaskTest.class, WordCounterTaskTest.class, MainITest.class, ApplicationTest.class })
public class AllTests {

}
