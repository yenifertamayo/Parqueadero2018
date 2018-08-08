package com.parqueadero2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest

public class IntegrationTest {

	@Test
	public void unitTest()
	{
		int suma = 8 + 8;
		assertEquals(suma, 16);
	}
	
}