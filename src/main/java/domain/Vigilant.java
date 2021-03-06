package domain;

import java.util.Calendar;
import java.util.List;

import domain.constants.MessageConstants;
import domain.repository.IBillRepository;
import domain.repository.IVehicleRepository;
import domain.rules.IExitRules;
import domain.rules.IIngressRules;
import exception.ParkingException;
import model.Bill;
import model.ParkedVehicle;
import model.Parking;
import model.Vehicle;

public class Vigilant {

	private IVehicleRepository iVehicleRepository;
	private IBillRepository iBillRepository;
	private List<IIngressRules> iIngressRules;
	private List<IExitRules> iExitRules;
	@SuppressWarnings("unused")
	private Parking parking;

	public Vigilant(IVehicleRepository iVehicleRepository, IBillRepository iBillRepository,
			List<IIngressRules> iIngressRules, List<IExitRules> iExitRules, Parking parking) {
		this.iVehicleRepository = iVehicleRepository;
		this.iBillRepository = iBillRepository;
		this.iIngressRules = iIngressRules;
		this.iExitRules = iExitRules;
		this.parking = parking;
	}

	public Bill vehicleRegistry(Vehicle vehicle, Calendar ingressDate) {

		vehicle.setPlate(convertToCapitalLetter(vehicle.getPlate()));
		
		isPossibleIngress(vehicle, ingressDate);

		if (validateVehicleRegistred(vehicle.getPlate())) {

			iVehicleRepository.vehicleRegistry(vehicle);
		}

		Bill bill = new Bill(ingressDate, null, vehicle, 0);
		iBillRepository.addBill(bill);
		return bill;
	}

	private boolean validateVehicleRegistred(String plate) {
		Vehicle vehicle = iVehicleRepository.getVehicleByPate(plate);
		return vehicle == null;
	}

	private void isPossibleIngress(Vehicle vehicle, Calendar ingressDate) {

		if (validateIsParked(vehicle.getPlate())) {
			throw new ParkingException(MessageConstants.VEHICLE_IS_PARKED);
		}

		validateIngressRules(vehicle, ingressDate);
	}

	private boolean validateIsParked(String plate) {

		Bill bill = iBillRepository.getBillByPlate(plate);
		return bill != null;
	}

	private void validateIngressRules(Vehicle vehicle, Calendar ingressDate) {
		for (IIngressRules ingressRules : iIngressRules) {
			
			ingressRules.validateRule(vehicle, ingressDate);
		}

	}
	
	public Bill vehicleExit(String plate, Calendar exitDate) {

		plate = convertToCapitalLetter(plate);
		
		if (validateIsParked(plate)) {

			Bill bill = iBillRepository.getBillByPlate(plate);
			bill.setExitDate(exitDate);
			
			validateExitRules(bill);
			iBillRepository.updateBill(bill);
			
			return bill;
		}

		throw new ParkingException(MessageConstants.VEHICLE_IS_NOT_PARKED);
	}

	private void validateExitRules(Bill bill) {

		for (IExitRules exitRule : iExitRules) {
			
			exitRule.valueToPay(bill);
		}
		
	}
	
	public List<ParkedVehicle> getListParked() {
		
		return iBillRepository.getListParked();
	}

	private String convertToCapitalLetter(String plate) {
	
		return plate.toUpperCase();
	}
	
}