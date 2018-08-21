package com.parking2018;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import domain.Vigilant;
import exception.ParkingException;
import model.Bill;
import model.Car;
import model.Motorcycle;
import model.ParkedVehicle;
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
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();				
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}
	
	@Test
	public void carRegistryPlatePlateStartWihtAMondayTest()
	{
		Vehicle vehicle = new Car("ASD123");
		Calendar ingressDate = DateBuilder.dateEqualToSunday();				
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}

	@Test
	public void carRegistryPlateStartWihtASundayTest()
	{
		Vehicle vehicle = new Car("ASD123");
		Calendar ingressDate = DateBuilder.dateEqualToSunday();				
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}
	
	
	@Test
	public void carRegistryPlateStartWihtATest(){
		
		Vehicle vehicle = new Car("ASD123");
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();				
		
		try {

			vigilant.vehicleRegistry(vehicle, ingressDate);
			fail();

		} catch (ParkingException e) {

			Assert.assertEquals("No autorizado. No es un día habil para su ingreso.", e.getMessage());
		}
	}
	
	@Test
	public void motorcycleRegistryTest()
	{
		Vehicle vehicle = new Motorcycle("SSD12B", 125);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();				
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}
	
	@Test
	public void motorcycleRegistryPlateStartWihtAMondayTest()
	{
		Vehicle vehicle = new Motorcycle("ASD12B", 125);
		Calendar ingressDate = DateBuilder.dateEqualToSunday();			
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}
	
	@Test
	public void motorcycleRegistryPlateSundayStartWihtATest()
	{
		Vehicle vehicle = new Motorcycle("ASD12B", 125);
		Calendar ingressDate = DateBuilder.dateEqualToSunday();			
		Bill bill =vigilant.vehicleRegistry(vehicle, ingressDate);
		assertEquals(bill.getVehicle(), vehicle);
	}
	
	@Test
	public void valueToPayCarTest(){
		
		Vehicle vehicle = new Car("SSD12B");
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDate();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(2000.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void valueToPayCarMore24HourTest(){
		
		Vehicle vehicle = new Car("SSD12B");
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDateMore24Hours();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(11000.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void valueToPayMotorcycleTest(){
		
		Vehicle vehicle = new Motorcycle("SSD12B", 125);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDate();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(1000.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void valueToPayMotorcycleMore24HourTest(){
		
		Vehicle vehicle = new Motorcycle("SSD12B", 125);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDateMore24Hours();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(5500.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void valueToPayMotorcycleDisplacementMajor500Test(){
		
		Vehicle vehicle = new Motorcycle("SSD12B", 501);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDateMore24Hours();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(7500.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void valueToPayMotorcycleMajor24Test(){
		
		Vehicle vehicle = new Motorcycle("SSD12B", 501);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);
		Calendar exitDate = DateBuilder.exitDateMore24And9Hours();
		Bill bill = vigilant.vehicleExit("SSD12B", exitDate);
		assertEquals(10000.0, bill.getValueToPay(), 0);
	}
	
	@Test
	public void carExitPlateNotParkedTest(){

		Calendar exitDate = DateBuilder.exitDateMore24And9Hours();

		try {
			vigilant.vehicleExit("SSD12B", exitDate);
			fail();

		} catch (ParkingException e) {

			Assert.assertEquals("El vehiculo no se encuntra parqueado actualmente.", e.getMessage());
		}
	}
	
	@Test
	public void carRegistryPlateParkedTest(){

		Vehicle vehicle = new Motorcycle("SSD12B", 501);
		Calendar ingressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(vehicle, ingressDate);

		try {
			vigilant.vehicleRegistry(vehicle, ingressDate);
			fail();

		} catch (ParkingException e) {

			Assert.assertEquals("El vehiculo ya esta en el parqueadero.", e.getMessage());
		}
	}
	
	@Test
	public void vehicleListTest(){
		
		Vehicle motorcycle = new Motorcycle("SSD12B", 125);
		Calendar motorcycleIngressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(motorcycle, motorcycleIngressDate);
		Vehicle car = new Car("BSD12B");
		Calendar carIngressDate = DateBuilder.dateDiferentToMonday();			
		vigilant.vehicleRegistry(car, carIngressDate);

		List<ParkedVehicle> parkedVehiclesList = vigilant.getListParked();
		
		assertEquals(2, parkedVehiclesList.size());
	}
}