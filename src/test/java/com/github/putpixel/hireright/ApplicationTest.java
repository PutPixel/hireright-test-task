package com.github.putpixel.hireright;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest extends Assert {

	private ByteArrayOutputStream baos;
	private PrintStream out;

	@Before
	public void before() {
		baos = new ByteArrayOutputStream();
		out = new PrintStream(baos);
	}

	private String out() {
		return baos.toString();
	}

	@Test
	public void github_v() throws Exception {
		String[] cmd = { "https://github.com", "Use,letter", "-v", "-c", "-w", "-e" };
		Arguments args = ArgumentParser.parse(cmd);
		args.setOutput(out);

		Main.launch(args);
		//Verbose
		assertThat(out(), containsString("Start program at"));
		assertThat(out(), containsString("Start retrieving content"));
		assertThat(out(), containsString("End retrieving content"));
		assertThat(out(), containsString("End program at"));

		//Tasks verbose
		assertThat(out(), containsString("Characters count on page"));
		assertThat(out(), containsString("Word(s) occurrence"));
		assertThat(out(), containsString("Start task 'Sentences with given words'"));


		//Content
		assertThat(out(), containsString("Use"));
		assertThat(out(), containsString("letter"));
		assertThat(out(), containsString("Use GitHub"));
		assertThat(out(), containsString("least one letter"));
		assertThat(out(), containsString("'Use' found  2 times"));
		assertThat(out(), containsString("'letter' found  1 times"));
	}

	@Test
	public void github_no_v() throws Exception {
		String[] cmd = { "https://github.com", "Use,letter", "-c", "-w", "-e" };
		Arguments args = ArgumentParser.parse(cmd);
		args.setOutput(out);

		Main.launch(args);
		//no verbose
		assertThat(out(), not(containsString("Start program at")));
		assertThat(out(), not(containsString("Start retrieving content")));
		assertThat(out(), not(containsString("End retrieving content")));
		assertThat(out(), not(containsString("End program at")));

		//Tasks no verbose
		assertThat(out(), not(containsString("Characters count on page")));
		assertThat(out(), not(containsString("Word(s) occurrence")));
		assertThat(out(), not(containsString("Start task 'Sentences’")));

		//Tasks body
		assertThat(out(), containsString("Words count on page"));
		assertThat(out(), containsString("Found sentences"));
		assertThat(out(), containsString("Total characters"));

		//Content
		assertThat(out(), containsString("Use"));
		assertThat(out(), containsString("letter"));
		assertThat(out(), containsString("Use GitHub"));
		assertThat(out(), containsString("least one letter"));
		assertThat(out(), containsString("'Use' found  2 times"));
		assertThat(out(), containsString("'letter' found  1 times"));
	}
}
