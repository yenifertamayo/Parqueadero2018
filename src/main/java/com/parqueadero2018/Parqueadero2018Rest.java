package com.parqueadero2018;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Vigilant;
import model.Car;
import model.Motorcycle;

@RestController
@Transactional
@RequestMapping(value = "/parking")
public class Parqueadero2018Rest {

	@Autowired
	Vigilant vigilant;
	
	@RequestMapping(value = "/registry/car", method = RequestMethod.POST)
	@ResponseBody
	public void carRegistry(@RequestBody Car car)
	{
		vigilant.vehicleRegistry(car);
	}
	
	
	@RequestMapping(value = "/registry/motorcycle", method = RequestMethod.POST)
	@ResponseBody
	public void carRegistry(@RequestBody Motorcycle motorcycle)
	{
		vigilant.vehicleRegistry(motorcycle);
	}
}
