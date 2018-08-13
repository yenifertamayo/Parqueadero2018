package dao.builder;

import dao.entity.VehicleEntity;
import model.Car;
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

	public static Vehicle convertToDto(VehicleEntity vehicleEntity) {
		
		Vehicle vehicle = null;
		
		if(vehicleEntity != null) 
		{
			if(vehicleEntity.getType().equals("Moto"))
			{
				vehicle = new Motorcycle(vehicleEntity.getPlate(), vehicleEntity.getDisplacement());
			}
			
			else 
			{
				vehicle = new Car(vehicleEntity.getPlate());
			}
		}
		
		return vehicle;
	}

}