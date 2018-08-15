package domain.rules;

import org.springframework.beans.factory.annotation.Autowired;

import model.Bill;
import model.Motorcycle;
import model.Parking;

public class MotorcycleDisplacement implements IExitRules{

	@Autowired
	public Parking parking;
	
	public MotorcycleDisplacement(Parking parking) {

		this.parking = parking;
	}

	@Override
	public double valueToPay(Bill bill) {
		
		if (bill.getVehicle() instanceof Motorcycle) {
			
			return validateMotorcycleDisplacement(bill);
		}
		
		return bill.getValueToPay();
	}

	private double validateMotorcycleDisplacement(Bill bill) {
		
		if (((Motorcycle) bill.getVehicle()).getDisplacement() >= parking.getMaxDisplacementMotorcycle()) {
			
			double value = bill.getValueToPay() + parking.getSurplusMotorcycle();
			bill.setValueToPay(value);
			
			return bill.getValueToPay();
		}
		
		return bill.getValueToPay();
	}

}