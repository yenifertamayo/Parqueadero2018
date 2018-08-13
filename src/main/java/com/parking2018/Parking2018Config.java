package com.parking2018;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import domain.Vigilant;
import domain.repository.IBillRepository;
import domain.repository.IVehicleRepository;
import domain.rules.IIngressRules;
import domain.rules.WorkshopCapacity;
import model.Parking;

@Configuration
public class Parking2018Config 
{
	@Bean
	public Vigilant createVigilant(IVehicleRepository iVehicleRepository, IBillRepository iBillRepository, Parking parking)
	{
		return new Vigilant(iVehicleRepository, iBillRepository, addIngressRules(parking), createParking());
	}
	
	private List<IIngressRules> addIngressRules(Parking parking) 
	{
		List<IIngressRules> listIngressRules = new ArrayList<>();
		listIngressRules.add(new WorkshopCapacity(parking));
		
		return listIngressRules;
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
    public CorsFilter corsFilter() 
	{
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