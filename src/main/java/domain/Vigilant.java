package domain;

import domain.repository.IVehicleRepository;
import model.Vehicle;

public class Vigilant {
	
	private IVehicleRepository iVehicleRepository;
	
	public Vigilant(IVehicleRepository iVehicleRepository) {
		
		this.iVehicleRepository = iVehicleRepository;
	}

	public void vehicleRegistry(Vehicle vehicle) {
		
		validateIngressRules();
		
		iVehicleRepository.vehicleRegistry(vehicle);
	}

	private void validateIngressRules() {
		// TODO Auto-generated method stub
		
	}

}