package com.example.ethenatest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EthenaTestApplicationTests {

	@Autowired
	GreetingController greetingController;

	@Test
	void testGreetingApi() {

	}

}
