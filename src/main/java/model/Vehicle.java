package model;

public class Vehicle 
{	
	String plate;
	
	public Vehicle() 
	{
	}
	
	public Vehicle(String plate) {
		super();
		this.plate = plate;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
}