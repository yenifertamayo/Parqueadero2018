package com.parqueadero2018;

import static org.junit.Assert.assertEquals;
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
	public void CarRegistryTest()
	{
		Vehicle vehicle = new Car("ASD123");
		vigilant.VehicleRegistry(vehicle);
		assertEquals(vehicle.plate, "ASD123");
	}

	@Test
	public void MotorcycleRegistryTest()
	{
		Vehicle vehicle = new Motorcycle("ASD12B", 125);
		vigilant.VehicleRegistry(vehicle);
		assertEquals(vehicle.plate, "ASD12B");
	}
}