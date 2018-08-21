package dao.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dao.builder.BillBuilder;
import dao.builder.VehicleBuilder;
import dao.entity.BillEntity;
import dao.entity.VehicleEntity;
import domain.constants.ParkingConstants;
import domain.repository.IBillRepository;
import model.Bill;
import model.ParkedVehicle;
import model.Motorcycle;
import model.Vehicle;

@Repository
public class BillDaoRepository implements IBillRepository {
	private static final String BILL_BY_PLATE = "Bill.findByPlate";
	private static final String PLATE = "plate";
	private static final String NUMBER_OF_VEHICLES = "Bill.countAllBill";
	private static final String TYPE = "type";
	private static final String ALL_PARKED = "Bill.findAllParked";
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
		if (billEntity != null) {
		
			billEntity.setExitDate(bill.getExitDate());
			billEntity.setValueToPay(bill.getValueToPay());
		}
		
	}

	@Override
	public List<ParkedVehicle> getListParked() {
		
		List<BillEntity> billEntityList = getBillEntityList();
		List<ParkedVehicle> parkedList = new ArrayList<>();
		
		if (billEntityList != null) {
			
			ParkedVehicle parkedVehicle;
			
			for (int i = 0; i < billEntityList.size(); i++) {
				
				Bill bill = BillBuilder.convertToDto(billEntityList.get(i));
				String vehicleType = validateVehicleType(bill.getVehicle());
				parkedVehicle = new ParkedVehicle(bill.getVehicle().getPlate(), vehicleType,
										bill.getIngressDate());
				parkedList.add(parkedVehicle);				
			}
			
		}
		
		return parkedList;
	}

	@SuppressWarnings("unchecked")
	private List<BillEntity> getBillEntityList() {

		Query query = entityManager.createNamedQuery(ALL_PARKED);
		List<BillEntity> resultList = query.getResultList();
		
		return !(resultList).isEmpty() ? resultList : null;
	}
	
	private String validateVehicleType(Vehicle vehicle) {

		if (vehicle instanceof Motorcycle) {
			
			return ParkingConstants.MOTORCYCLE;
		}

		return ParkingConstants.CAR;
	}

}