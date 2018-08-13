package dao.builder;

import dao.entity.VehicleEntity;
import model.Motorcycle;
import model.Vehicle;

public class VehicleBuilder 
{
	public static VehicleEntity convertToEntity(Vehicle vehicle) 
	{
		VehicleEntity vehicleEntity = new VehicleEntity();

		if (vehicle instanceof Motorcycle) {
			vehicleEntity.setType("Moto");
			vehicleEntity.setPlate(vehicle.getPlate());
			vehicleEntity.setDisplacement(((Motorcycle) vehicle).getDisplacement());
		}
		
		else {
			vehicleEntity.setType("Carro");
			vehicleEntity.setPlate(vehicle.getPlate());
			vehicleEntity.setDisplacement(0);
		}
		
		return vehicleEntity;
	}

}