package domain;

import domain.repository.IVehicleRepository;
import model.Vehicle;

public class Vigilant {
	
	private IVehicleRepository iVehicleRepository;
	
	public Vigilant(IVehicleRepository iVehicleRepository) {
		
		this.iVehicleRepository = iVehicleRepository;
	}

	public void vehicleRegistry(Vehicle vehicle) {
		iVehicleRepository.vehicleRegistry(vehicle);
	}

}