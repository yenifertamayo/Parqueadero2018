package com.parqueadero2018;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import domain.Vigilant;
import domain.repository.IVehicleRepository;

@Configuration
public class Parqueadero2018Config {
	
	@Bean
	public Vigilant createVigilant(IVehicleRepository iVehicleRepository)
	{
		return new Vigilant(iVehicleRepository);
		
	}
	
} 