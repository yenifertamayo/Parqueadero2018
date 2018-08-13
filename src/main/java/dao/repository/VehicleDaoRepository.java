package dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import dao.builder.VehicleBuilder;
import dao.entity.VehicleEntity;
import domain.repository.IVehicleRepository;
import model.Vehicle;

@Repository
public class VehicleDaoRepository implements IVehicleRepository
{
	private static final String VEHICLE_BY_PLATE = "Vehicle.findByPlate";
	private static final String PLATE = "plate";
	private EntityManager entityManager;

	public VehicleDaoRepository(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	@Override
	public void vehicleRegistry(Vehicle vehicle) 
	{
		VehicleEntity vehicleEntity = VehicleBuilder.convertToEntity(vehicle);
		entityManager.persist(vehicleEntity);
	}

	@SuppressWarnings("rawtypes")
	public VehicleEntity obtenerVehicleEntity(String plate) {

		Query query = entityManager.createNamedQuery(VEHICLE_BY_PLATE);
		query.setParameter(PLATE, plate);
		
		List resultList = query.getResultList();
		return !(resultList).isEmpty() ? (VehicleEntity) resultList.get(0): null;
		
	}

}