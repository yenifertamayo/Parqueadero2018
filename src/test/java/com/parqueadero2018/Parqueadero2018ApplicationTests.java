package com.parqueadero2018;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class Parqueadero2018ApplicationTests {
	
	@Autowired
	Parqueadero2018Application parqueaderoApplication;
	
	@Test
	public void unitTest()
	{
		int suma = 8 + 8;
		assertEquals(suma, 16);
	}
}