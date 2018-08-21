package domain.rules;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import domain.repository.IBillRepository;
import exception.ParkingException;
import model.Car;
import model.Parking;
import model.Vehicle;

public class WorkshopCapacity implements IIngressRules {

	@Autowired
	Parking parking;
	IBillRepository iBillRepository;

	public WorkshopCapacity(IBillRepository iBillRepository, Parking parking) {

		this.iBillRepository = iBillRepository;
		this.parking = parking;
	}

	@Override
	public void validateRule(Vehicle vehicle, Calendar ingressDate) {

		Long numberOfVehicles = iBillRepository.getNumberOfVehicles(vehicle);

		if (vehicle instanceof Car) {
			validateCapacityCar(numberOfVehicles);
		}

		else {
			
			validateCapacityMotorcycle(numberOfVehicles);
		}
	}

	private void validateCapacityCar(Long numberOfVehicles) {

		if (numberOfVehicles >= 2) {

			throw new ParkingException("No hay cupo para carro");
		}
	}

	private void validateCapacityMotorcycle(Long numberOfVehicles) {

		if (numberOfVehicles >= 1) {
			throw new ParkingException("No hay cupo para moto");
		}
	}

}