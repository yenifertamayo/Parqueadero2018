package dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.builder.BillBuilder;
import dao.builder.VehicleBuilder;
import dao.entity.BillEntity;
import dao.entity.VehicleEntity;
import domain.repository.IBillRepository;
import model.Bill;
import model.Vehicle;

@Repository
public class BillDaoRepository implements IBillRepository {
	private static final String BILL_BY_PLATE = "Bill.findByPlate";
	private static final String PLATE = "plate";
	private static final String NUMBER_OF_VEHICLES = "Bill.countAllBill";
	private static final String TYPE = "type";
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
	public Bill getBillByPlate(String plate) {
		
		Query query = entityManager.createNamedQuery(BILL_BY_PLATE);
		query.setParameter(PLATE, plate);
		
		BillEntity billEntity = getBillEntityByPlate(plate);
		Bill bill = null;
		
		if (billEntity != null) {
			
			bill = BillBuilder.convertToDto(billEntity);
		}
		
		return bill;
	}
	
	private BillEntity getBillEntityByPlate(String plate) {
		
		Query query = entityManager.createNamedQuery(BILL_BY_PLATE);
		query.setParameter(PLATE, plate);
		
		List<?> resultList = query.getResultList();
		return !(resultList).isEmpty() ? (BillEntity) resultList.get(0) : null;
	}


	@Override
	public Long getNumberOfVehicles(Vehicle vehicle) {
		
		VehicleEntity vehicleEntity = VehicleBuilder.convertToEntity(vehicle);
		Query query = entityManager.createNamedQuery(NUMBER_OF_VEHICLES);
		query.setParameter(TYPE, vehicleEntity.getType());
		
		return (Long) query.getSingleResult();
	}

	@Override
	public void updateBill(Bill bill) {
		BillEntity billEntity = getBillEntityByPlate(bill.getVehicle().getPlate());
		billEntity.setExitDate(bill.getExitDate());
		billEntity.setValueToPay(bill.getValueToPay());
		
	}

}