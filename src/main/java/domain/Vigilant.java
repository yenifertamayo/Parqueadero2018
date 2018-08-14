package domain;

import java.util.Calendar;
import java.util.List;

import domain.repository.IBillRepository;
import domain.repository.IVehicleRepository;
import domain.rules.IIngressRules;
import exception.ParkingException;
import model.Bill;
import model.Parking;
import model.Vehicle;

public class Vigilant {
	private IVehicleRepository iVehicleRepository;
	private List<IIngressRules> iIngressRules;
	private IBillRepository iBillRepository;
	@SuppressWarnings("unused")
	private Parking parking;

	public Vigilant(IVehicleRepository iVehicleRepository, IBillRepository iBillRepository,
			List<IIngressRules> iIngressRules, Parking parking) {
		this.iVehicleRepository = iVehicleRepository;
		this.iBillRepository = iBillRepository;
		this.iIngressRules = iIngressRules;
		this.parking = parking;
	}

	public Bill vehicleRegistry(Vehicle vehicle, Calendar ingressDate) {

		isPossibleIngress(vehicle, ingressDate);
		
		if(validateVehicleRegistred(vehicle.plate)) {
			
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
		
		Vehicle vehicleParked = iBillRepository.getVehicleBillByPlate(plate);
		return vehicleParked != null;
	}

	private void validateIngressRules(Vehicle vehicle, Calendar ingressDate) {
		for (IIngressRules rules : iIngressRules) {
			if (rules.validateRule(vehicle, ingressDate)) {
				continue;
			}
		}

	}

}