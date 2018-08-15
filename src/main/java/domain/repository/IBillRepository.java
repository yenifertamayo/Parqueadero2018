package domain.repository;

import java.util.List;

import model.Bill;
import model.ParkedVehicle;
import model.Vehicle;

public interface IBillRepository 
{
	void addBill(Bill bill);

	Long getNumberOfVehicles(Vehicle vehicle);

	void updateBill(Bill bill);
	
	Bill getBillByPlate(String plate);

	List<ParkedVehicle> getListParked();

}
