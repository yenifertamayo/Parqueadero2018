package domain.rules;

import org.springframework.beans.factory.annotation.Autowired;

import model.Bill;
import model.Car;
import model.Parking;

public class TotalToPay implements IExitRules{

	@Autowired
	public Parking parking;
	
	public TotalToPay(Parking parking) {
		
		this.parking = parking;
	}
	
	@Override
	public double valueToPay(Bill bill) {

		double initDate = bill.getIngressDate().getTimeInMillis();
		double endDate = bill.getExitDate().getTimeInMillis();
		double exactTimeInWorkshop = endDate - initDate;
		
		double hoursNumber = calculateHours(exactTimeInWorkshop);
		double daysNumber = calculateDays(exactTimeInWorkshop);
		
		if (bill.getVehicle() instanceof Car) {
			
			return calculateCarValueToPay(bill, hoursNumber, daysNumber);
		}
		
		return calculateMotorcycleValueToPay(bill, hoursNumber, daysNumber);
		
	}

	private double calculateHours(double exactTimeInWorkshop) {

		double hours = Math.ceil((exactTimeInWorkshop / (60 * 60 * 1000)));
		
		for(int i = 24; hours >= i; i+=24){
			
			hours -= 24;
		}
		
		if(hours >= 9 || hours < 0){
			
			hours = 0;
		}
		
		return hours;
	}

	private int calculateDays(double exactTimeInWorkshop) {
		
		double hours = Math.ceil((exactTimeInWorkshop / (60 * 60 * 1000)));
		double days = 0;
		
		if (hours >= 24) {
			
			days += (int) Math.floor((exactTimeInWorkshop / (24 * 60 * 60 * 1000))) % 24;
			hours -= days* 24;
		}
		
		if (hours >= 9) {
			
			days += 1;
		}
		
		return (int) days;
	}

	private double calculateCarValueToPay(Bill bill, double hoursNumber, double daysNumber) {

		double totalToPay = hoursNumber*parking.getValueHourCar()+ daysNumber*parking.getValueDayCar();
		
		bill.setValueToPay(totalToPay + bill.getValueToPay());
		return bill.getValueToPay();
	}
	
	private double calculateMotorcycleValueToPay(Bill bill, double hoursNumber, double daysNumber) {

		double totalToPay = hoursNumber*parking.getValueHourMotorcycle() + daysNumber*parking.getValueDayMotorcycle();

		bill.setValueToPay(totalToPay + bill.getValueToPay());
		return bill.getValueToPay();
	}

}