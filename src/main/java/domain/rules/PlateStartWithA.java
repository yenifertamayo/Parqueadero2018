package domain.rules;

import java.util.Calendar;

import domain.constants.MessageConstants;
import domain.constants.ParkingConstants;
import exception.ParkingException;
import model.Vehicle;

public class PlateStartWithA implements IIngressRules {
	
	private Vehicle vehicle;
	private Calendar ingressDate;

	@Override
	public void validateRule(Vehicle vehicle, Calendar ingressDate) {
		this.vehicle = vehicle;
		this.ingressDate = ingressDate;
		validatePlate();
	}

	private boolean validatePlate() {
		if (vehicle.getPlate().startsWith(ParkingConstants.FIRST_WORD_RULE)) {
			return validateDate();
		}

		return true;
	}

	private boolean validateDate() {
		if ((ingressDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
				|| ingressDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		}

		throw new ParkingException(MessageConstants.NOT_AUTORIZED);
	}

}