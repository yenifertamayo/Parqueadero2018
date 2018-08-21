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
import domain.rules.IExitRules;
import domain.rules.IIngressRules;
import domain.rules.MotorcycleDisplacement;
import domain.rules.PlateStartWithA;
import domain.rules.TotalToPay;
import domain.rules.WorkshopCapacity;
import model.Parking;

@Configuration
public class Parking2018Config {
	
	@Bean
	public Vigilant createVigilant(IVehicleRepository iVehicleRepository, IBillRepository iBillRepository,
			Parking parking) {
		return new Vigilant(iVehicleRepository, iBillRepository, addIngressRules(iBillRepository, parking),
							addExitRules(parking) ,createParking());
	}

	private List<IIngressRules> addIngressRules(IBillRepository iBillRepository, Parking parking) {
		List<IIngressRules> listIngressRules = new ArrayList<>();
		listIngressRules.add(new PlateStartWithA());
		listIngressRules.add(new WorkshopCapacity(iBillRepository, parking));

		return listIngressRules;
	}
	
	private List<IExitRules> addExitRules(Parking parking) {

		List<IExitRules> listExitRules = new ArrayList<>();
		listExitRules.add(new MotorcycleDisplacement(parking));
		listExitRules.add(new TotalToPay(parking));
		return listExitRules;
	}

	@Bean
	public Parking createParking() {

		Parking parking = new Parking();
		parking.setMaxCars(20);
		parking.setMaxMotorcycles(10);
		parking.setMaxDisplacementMotorcycle(500);
		parking.setSurplusMotorcycle(2000);
		parking.setValueHourCar(1000);
		parking.setValueDayCar(8000);
		parking.setValueHourMotorcycle(500);
		parking.setValueDayMotorcycle(4000);
		
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