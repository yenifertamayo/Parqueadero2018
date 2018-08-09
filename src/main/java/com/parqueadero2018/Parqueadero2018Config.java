package com.parqueadero2018;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import domain.Vigilant;
import domain.repository.IVehicleRepository;
import model.Parking;

@Configuration
public class Parqueadero2018Config {
	
	@Bean
	public Vigilant createVigilant(IVehicleRepository iVehicleRepository)
	{
		return new Vigilant(iVehicleRepository);
		
	}
	
	@Bean
	public Parking createParking()
	{	
		int maxCars = 20;
		int maxMotorcycles = 10;
		int maxDisplacementMotorcycle = 500;
		double surplusMotorcycle = 2000;
		double valueHourCar = 1000;
		double valueDayCar = 8000;
		double valueHourMotorcycle = 500;
		double valueDayMotorcycle = 4000;
		Parking parking = new Parking(
				maxCars, maxMotorcycles, maxDisplacementMotorcycle, surplusMotorcycle,
				valueHourCar, valueDayCar, valueHourMotorcycle, valueDayMotorcycle);
		
		return parking;
	}
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
	}
	
} 