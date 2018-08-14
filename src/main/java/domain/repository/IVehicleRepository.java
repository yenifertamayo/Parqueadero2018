package domain.repository;

import model.Vehicle;

public interface IVehicleRepository 
{
	public void vehicleRegistry(Vehicle vehicle);

	public Vehicle getVehicleByPate(String plate);

}