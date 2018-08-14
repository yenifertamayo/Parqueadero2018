package dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.builder.BillBuilder;
import dao.entity.BillEntity;
import dao.entity.VehicleEntity;
import domain.repository.IBillRepository;
import model.Bill;
import model.Vehicle;

@Repository
public class BillDaoRepository implements IBillRepository {
	private static final String BILL_BY_PLATE = "Bill.findByPlate";
	private static final String PLATE = "plate";
	private EntityManager entityManager;

	public BillDaoRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void addBill(Bill bill) {
		BillEntity billEntity = BillBuilder.convertToEntity(bill);
		VehicleDaoRepository vehicleDaoRepository = new VehicleDaoRepository(entityManager);
		VehicleEntity vehicleEntity = vehicleDaoRepository.getVehicleEntity(bill.getVehicle().getPlate());
		billEntity.setVehicleEntity(vehicleEntity);
		entityManager.persist(billEntity);
	}

	@Override
	public Vehicle getVehicleBillByPlate(String plate) {

		Vehicle vehicle = null;
		BillEntity billEntity = getBillByPlate(plate);
		if(billEntity != null)
		{
			Bill bill = BillBuilder.convertToDto(billEntity);
			vehicle = bill.getVehicle();
		}
		
		return vehicle;
	}

	@SuppressWarnings("rawtypes")
	private BillEntity getBillByPlate(String plate) {
		
		Query query = entityManager.createNamedQuery(BILL_BY_PLATE);
		query.setParameter(PLATE, plate);
		
		List resultList = query.getResultList();
		return !(resultList).isEmpty() ? (BillEntity) resultList.get(0) : null;
	}

}