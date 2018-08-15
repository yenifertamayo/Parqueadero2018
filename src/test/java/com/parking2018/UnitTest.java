package com.parking2018;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import domain.Vigilant;
import model.Car;
import model.Motorcycle;
import model.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class UnitTest {
	
	@Autowired(required = true)
	Vigilant vigilant;
	
	@Test
	public void carRegistryTest()
	{
		Vehicle vehicle = new Car("SSD123");
		vigilant.vehicleRegistry(vehicle, Calendar.getInstance());
		assertEquals("SSD123", vehicle.getPlate());
	}

	@Test
	public void motorcycleRegistryTest()
	{
		Vehicle vehicle = new Motorcycle("SSD12B", 125);
		vigilant.vehicleRegistry(vehicle, Calendar.getInstance());
		assertEquals("SSD12B", vehicle.getPlate());
	}
}