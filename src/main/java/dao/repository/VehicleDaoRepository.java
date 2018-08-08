package dao.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import dao.builder.VehicleBuilder;
import dao.entity.VehicleEntity;
import domain.repository.IVehicleRepository;
import model.Vehicle;

@Repository
public class VehicleDaoRepository implements IVehicleRepository{

	private EntityManager entityManager;

	public VehicleDaoRepository(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	@Override
	public void vehicleRegistry(Vehicle vehicle) {
		
		VehicleEntity vehicleEntity = VehicleBuilder.ConvertToEntity(vehicle);
		entityManager.persist(vehicleEntity);
	}

}