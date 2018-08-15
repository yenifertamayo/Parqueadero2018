package domain;

import java.util.Calendar;
import java.util.List;

import domain.repository.IBillRepository;
import domain.repository.IVehicleRepository;
import domain.rules.IExitRules;
import domain.rules.IIngressRules;
import exception.ParkingException;
import model.Bill;
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

		isPossibleIngress(vehicle, ingressDate);

		if (validateVehicleRegistred(vehicle.plate)) {

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
			throw new ParkingException("El vehiculo ya esta en el parqueadero");
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
	
	public Bill vehicleExit(String plate) {

		if (validateIsParked(plate)) {

			Bill bill = iBillRepository.getBillByPlate(plate);
			Calendar exitDate = Calendar.getInstance();
			bill.setExitDate(exitDate);
			
			validateExitRules(bill);
			iBillRepository.updateBill(bill);
			
			return bill;
		}

		throw new ParkingException("El vehiculo no se encuntra parqueado actualmente");
	}

	private void validateExitRules(Bill bill) {

		for (IExitRules exitRule : iExitRules) {
			
			exitRule.valueToPay(bill);
		}
		
	}

}