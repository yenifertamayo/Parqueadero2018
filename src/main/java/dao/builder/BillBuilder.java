package dao.builder;

import dao.entity.BillEntity;
import model.Bill;

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

}
