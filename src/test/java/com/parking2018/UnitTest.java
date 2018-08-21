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
import domain.constants.MessageConstants;
import exception.ParkingException;
import model.Bill;
import model.Car;
import model.Motorcycle;
import model.ParkedVehicle;
import model.Parking;
import model.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class UnitTest {
	
	@Autowired
	Vigilant vigilant;
	
	@Autowired
	Parking parking;
	
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

			Assert.assertEquals(MessageConstants.NOT_AUTORIZED, e.getMessage());
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

			Assert.assertEquals(MessageConstants.VEHICLE_IS_NOT_PARKED, e.getMessage());
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

			Assert.assertEquals(MessageConstants.VEHICLE_IS_PARKED, e.getMessage());
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
	
	@Test
	public void vehicleListEmptyTest(){
		
		List<ParkedVehicle> parkedVehiclesList = vigilant.getListParked();
		assertEquals(0, parkedVehiclesList.size());
	}
	
	@Test
	public void carRegistry20Cars(){

		Calendar ingressDate = DateBuilder.dateDiferentToMonday();	
		Vehicle car1 = new Car("NSD12B");
		vigilant.vehicleRegistry(car1, ingressDate);
		Vehicle car2 = new Car("SSD12B");
		vigilant.vehicleRegistry(car2, ingressDate);
		parking.setMaxCars(2);

		Vehicle car3 = new Car("MSD12B");
		
		try {
			vigilant.vehicleRegistry(car3, ingressDate);
			fail();

		} catch (ParkingException e) {

			Assert.assertEquals(MessageConstants.NO_CAR_SPACE, e.getMessage());
		}
	}
	
	@Test
	public void carRegistry10Motorcycles(){

		Calendar ingressDate = DateBuilder.dateDiferentToMonday();	
		Vehicle motorcycle1 = new Motorcycle("NSD12B", 125);
		vigilant.vehicleRegistry(motorcycle1, ingressDate);
		Vehicle motorcycle2 = new Motorcycle("SSD12B", 125);
		vigilant.vehicleRegistry(motorcycle2, ingressDate);
		parking.setMaxMotorcycles(2);

		Vehicle motorcycle3 = new Motorcycle("MSD12B", 125);
		
		try {
			vigilant.vehicleRegistry(motorcycle3, ingressDate);
			fail();

		} catch (ParkingException e) {

			Assert.assertEquals(MessageConstants.NO_MOTORCYCLE_SPACE, e.getMessage());
		}
	}
}