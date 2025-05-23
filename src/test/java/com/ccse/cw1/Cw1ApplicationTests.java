package com.ccse.cw1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-ci.properties")
class Cw1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
