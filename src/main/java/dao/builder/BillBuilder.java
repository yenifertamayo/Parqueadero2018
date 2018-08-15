package dao.builder;

import dao.entity.BillEntity;
import model.Bill;
import model.Vehicle;

public class BillBuilder 
{
	public static BillEntity convertToEntity(Bill bill) 
	{
		BillEntity billEntity = new BillEntity();
		billEntity.setIngressDate(bill.getIngressDate());
		billEntity.setExitDate(bill.getExitDate());
		billEntity.setValueToPay(bill.getValueToPay());
		
		return billEntity;
	}

	public static Bill convertToDto(BillEntity billEntity) {
		
		Vehicle vehicle = VehicleBuilder.convertToDto(billEntity.getVehicle());
		Bill bill = new Bill();
		bill.setIngressDate(billEntity.getIngressDate());
		bill.setExitDate(billEntity.getExitDate());
		bill.setValueToPay(billEntity.getValueToPay());
		bill.setVehicle(vehicle);	
		
		return bill;
	}

}
