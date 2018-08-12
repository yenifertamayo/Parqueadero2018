package domain;

import java.util.Calendar;
import java.util.List;

import domain.repository.IVehicleRepository;
import domain.rules.IIngressRules;
import exception.ParkingException;
import model.Parking;
import model.Vehicle;

public class Vigilant {
	
	private IVehicleRepository iVehicleRepository;
	private List<IIngressRules> iIngressRules;
	@SuppressWarnings("unused")
	private Parking parking;
	
	public Vigilant(IVehicleRepository iVehicleRepository, List<IIngressRules> iIngressRules, Parking parking) {
		
		this.iVehicleRepository = iVehicleRepository;
		this.iIngressRules = iIngressRules;
		this.parking = parking;
	}

	public void vehicleRegistry(Vehicle vehicle, Calendar ingressDate)
	{
		if(validateIngressRules(vehicle, ingressDate))
		{
			iVehicleRepository.vehicleRegistry(vehicle);
		}

	}

	private boolean validateIngressRules(Vehicle vehicle, Calendar ingressDate)
	{
		for (IIngressRules rules: iIngressRules) 
		{
			if(rules.isPossibleIngress(vehicle, ingressDate))
			{
				return true;
			}
		}
		
		return false;
	}
	
	

}