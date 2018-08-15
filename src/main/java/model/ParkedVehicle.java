package model;

import java.util.Calendar;

public class ParkedVehicle {
	
	String plate;
	String type;
	Calendar ingressDate;
	
	public ParkedVehicle(String plate, String type, Calendar ingressDate) {
		this.plate = plate;
		this.type = type;
		this.ingressDate = ingressDate;
	}

	public ParkedVehicle() {
		
	}

	public String getPlate() {
		return plate;
	}

	public String getType() {
		return type;
	}

	public Calendar getIngressDate() {
		return ingressDate;
	}

}