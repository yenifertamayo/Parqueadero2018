package domain.rules;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import model.Car;
import model.Parking;
import model.Vehicle;

public class WorkshopCapacity implements IIngressRules
{
	@Autowired
	public Parking parking;
	
	public WorkshopCapacity(Parking parking) 
	{
		this.parking = parking;
	}

	@Override
	public boolean isPossibleIngress(Vehicle vehicle, Calendar ingressDate) 
	{
		if(vehicle instanceof Car)
		{
			return validateCapacityCar();
		}
		
		return validateCapacityMotorcycle();
	}

	private boolean validateCapacityCar() 
	{
		return true;
	}


	private boolean validateCapacityMotorcycle() 
	{
		return true;
	}

}