package com.parking2018;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Vigilant;
import model.Bill;
import model.Car;
import model.Motorcycle;
import model.ParkedVehicle;
import model.Vehicle;

@RestController
@Transactional
@RequestMapping(value = "/parking")
public class Parking2018Rest 
{
	@Autowired
	Vigilant vigilant;
	
	@RequestMapping(value = "/registry/car", method = RequestMethod.POST)
	@ResponseBody
	public Bill carRegistry(@RequestBody Car car)
	{
		Calendar ingressDate =  Calendar.getInstance();
		return vigilant.vehicleRegistry(car, ingressDate);
	}
	
	@RequestMapping(value = "/registry/motorcycle", method = RequestMethod.POST)
	@ResponseBody
	public Bill carRegistry(@RequestBody Motorcycle motorcycle)
	{
		Calendar ingressDate =  Calendar.getInstance();
		return vigilant.vehicleRegistry(motorcycle, ingressDate);
	}
	
	@RequestMapping(value = "/exit/car", method = RequestMethod.POST)
	@ResponseBody
	public Bill vehicleExit(@RequestBody Vehicle vehicle)
	{
		Calendar exitDate =  Calendar.getInstance();
		return vigilant.vehicleExit(vehicle.getPlate(), exitDate);
	}
	
	
	@RequestMapping(value = "/list/parked", method = RequestMethod.GET)
	@ResponseBody
	public List<ParkedVehicle> getListParked()
	{
		return vigilant.getListParked();
	}
}