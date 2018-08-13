package model;

import java.util.Calendar;

public class Bill 
{
	public Calendar ingressDate;
	public Calendar exitDate;
	public Vehicle vehicle;
	public double valueToPay;
	
	public Bill(Calendar ingressDate, Calendar exitDate, Vehicle vehicle, double valueToPay) {
		super();
		this.ingressDate = ingressDate;
		this.exitDate = exitDate;
		this.vehicle = vehicle;
		this.valueToPay = valueToPay;
	}

	public Calendar getIngressDate() {
		return ingressDate;
	}

	public Calendar getExitDate() {
		return exitDate;
	}

	public void setExitDate(Calendar exitDate) {
		this.exitDate = exitDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public double getValueToPay() {
		return valueToPay;
	}

	public void setValueToPay(double valueToPay) {
		this.valueToPay = valueToPay;
	}

}