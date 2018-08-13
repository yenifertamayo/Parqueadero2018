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

public class Vigilant 
{
	private IVehicleRepository iVehicleRepository;
	private List<IIngressRules> iIngressRules;
	private IBillRepository iBillRepository;
	@SuppressWarnings("unused")
	private Parking parking;
	
	public Vigilant(IVehicleRepository iVehicleRepository, IBillRepository iBillRepository, List<IIngressRules> iIngressRules, Parking parking) 
	{
		this.iVehicleRepository = iVehicleRepository;
		this.iBillRepository = iBillRepository;
		this.iIngressRules = iIngressRules;
		this.parking = parking;
	}

	public void vehicleRegistry(Vehicle vehicle, Calendar ingressDate)
	{
		if(validateIngressRules(vehicle, ingressDate))
		{
			iVehicleRepository.vehicleRegistry(vehicle);
			Bill bill = new Bill(ingressDate, null, vehicle, 0);
			iBillRepository.addBill(bill);
		}

	}

	private boolean validateIngressRules(Vehicle vehicle, Calendar ingressDate)
	{
		for (IIngressRules rules: iIngressRules) 
		{
			if(rules.isPossibleIngress(vehicle, ingressDate))
			{
				return true;
			}
		}
		
		return false;
	}
	
}