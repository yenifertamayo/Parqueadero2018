package domain.repository;

import model.Bill;
import model.Vehicle;

public interface IBillRepository 
{
	public void addBill(Bill bill);

	public Vehicle getVehicleBillByPlate(String plate);

}
