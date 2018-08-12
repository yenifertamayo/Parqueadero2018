package domain.rules;

import java.util.Calendar;

import exception.ParkingException;
import model.Vehicle;

public class PlateStartWithA implements IIngressRules
{
	private Vehicle vehicle;
	private Calendar ingressDate;
	@Override
	public boolean isPossibleIngress(Vehicle vehicle, Calendar ingressDate) 
	{
		this.vehicle = vehicle;
		this.ingressDate = ingressDate;
		return ValidatePlate();
	}

	private boolean ValidatePlate() 
	{
		if(vehicle.getPlate().startsWith("A"))
		{
			return validateDate();
		}
		
		return true;
	}

	private boolean validateDate() {
		
		if((ingressDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ||
			ingressDate.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
		{
			return true;
		}
		
		throw new ParkingException("No autorizado. No es un día habil para su ingreso.");
	}

}