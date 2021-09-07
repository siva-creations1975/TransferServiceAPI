package com.natwest.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransferServiceAppIntegrationTests {

	@Test
	public void contextLoads() {
	}

}
