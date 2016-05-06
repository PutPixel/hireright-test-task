package com.github.putpixel.hireright;

import org.junit.Test;

public class ApplicationITest {

	//@Test
	public void github() {
		String[] cmd = { "https://github.com", "Use,letter", "-v", "-c", "-w", "-e" };
		Main.main(cmd);
	}

//	@Test
	public void reddit() {
		String[] cmd = { "https://www.reddit.com/r/gaming/comments/4i4t58/when_instinct_takes_over/;https://www.reddit.com/r/funny/comments/4i4u7u/michael_the_mute/", "baby,you",
				"-c", "-w", "-e" };
		Main.main(cmd);
	}

}
