package dao.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="Bill")
public class BillEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_VEHICULO")
	private VehicleEntity vehicleEntity;
	private Calendar ingressDate;
	private Calendar exitDate;
	private double valueToPay;
	
	public VehicleEntity getVehicleEntity() {
		return vehicleEntity;
	}
	public void setVehicleEntity(VehicleEntity vehicleEntity) {
		this.vehicleEntity = vehicleEntity;
	}
	public Calendar getIngressDate() {
		return ingressDate;
	}
	public void setIngressDate(Calendar ingressDate) {
		this.ingressDate = ingressDate;
	}
	public Calendar getExitDate() {
		return exitDate;
	}
	public void setExitDate(Calendar exitDate) {
		this.exitDate = exitDate;
	}
	public double getValueToPay() {
		return valueToPay;
	}
	public void setValueToPay(double valueToPay) {
		this.valueToPay = valueToPay;
	}
	
}