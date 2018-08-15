package domain.repository;

import model.Vehicle;

public interface IVehicleRepository 
{
	void vehicleRegistry(Vehicle vehicle);

	Vehicle getVehicleByPate(String plate);

}