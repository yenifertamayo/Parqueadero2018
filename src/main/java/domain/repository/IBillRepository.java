package domain.repository;

import model.Bill;
import model.Vehicle;

public interface IBillRepository 
{
	public void addBill(Bill bill);

	public Long getNumberOfVehicles(Vehicle vehicle);

	public void updateBill(Bill bill);
	
	Bill getBillByPlate(String plate);

}
