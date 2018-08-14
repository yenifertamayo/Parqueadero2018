package domain.rules;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import dao.repository.BillDaoRepository;
import domain.repository.IBillRepository;
import exception.ParkingException;
import model.Car;
import model.Parking;
import model.Vehicle;

public class WorkshopCapacity implements IIngressRules {
	
	@Autowired
	public Parking parking;
	public IBillRepository iBillRepository;

	public WorkshopCapacity(IBillRepository iBillRepository, Parking parking) {
		
		this.iBillRepository = iBillRepository;
		this.parking = parking;
	}

	@Override
	public boolean validateRule(Vehicle vehicle, Calendar ingressDate) {
		
		Long numberOfVehicles = iBillRepository.getNumberOfVehicles(vehicle);
		
		if (vehicle instanceof Car) {
			return validateCapacityCar(numberOfVehicles);
		}

		return validateCapacityMotorcycle(numberOfVehicles);
	}

	private boolean validateCapacityCar(Long numberOfVehicles) {
		
		if(numberOfVehicles >= parking.getMaxCars()) {
	
			throw new ParkingException("No hay cupo para carro");
		}
		
		return true;
	}

	private boolean validateCapacityMotorcycle(Long numberOfVehicles) {
		
		if(numberOfVehicles >= parking.getMaxMotorcycles()) {
			throw new ParkingException("No hay cupo para moto");
		}

		return true;
	}

}