package dao.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name="Bill")
@NamedQueries({@NamedQuery(name = "Bill.findByPlate", query= "SELECT bill FROM Bill bill WHERE bill.vehicleEntity.plate = :plate AND bill.exitDate is null"),
@NamedQuery(name = "Bill.countAllBill", query = "SELECT COUNT(*) from Bill bill where bill.vehicleEntity.type =:type AND bill.exitDate is null")})

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
	
	public VehicleEntity getVehicle() {
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