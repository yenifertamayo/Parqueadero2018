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

@RestController
@Transactional
@RequestMapping(value = "/parqueadero")
public class Parqueadero2018Rest {

	@Autowired
	Vigilant vigilant;
	
	@RequestMapping(value = "/registro/carro", method = RequestMethod.POST)
	@ResponseBody
	public void CarRegistry(@RequestBody Car car)
	{
		vigilant.vehicleRegistry(car);
	}
	
}
