package dao.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import dao.builder.BillBuilder;
import dao.entity.BillEntity;
import dao.entity.VehicleEntity;
import domain.repository.IBillRepository;
import model.Bill;

@Repository
public class BillDaoRepository implements IBillRepository
{
	private EntityManager entityManager;
	
	public BillDaoRepository(EntityManager entityManager) 
	{
		this.entityManager = entityManager;
	}

	@Override
	public void addBill(Bill bill) 
	{
		BillEntity billEntity = BillBuilder.convertToEntity(bill);
		VehicleDaoRepository vehicleDaoRepository = new VehicleDaoRepository(entityManager);
		VehicleEntity vehicleEntity = vehicleDaoRepository.obtenerVehicleEntity(bill.getVehicle().getPlate());
		billEntity.setVehicleEntity(vehicleEntity);
		entityManager.persist(billEntity);
	}
 
}