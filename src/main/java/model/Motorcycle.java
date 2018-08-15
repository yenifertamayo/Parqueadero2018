package model;

public class Motorcycle extends Vehicle
{
	int displacement;
	
	public Motorcycle() {
	}
	
	public Motorcycle(String plate, int displacement) 
	{
		super(plate);
		this.displacement = displacement;
	}

	public int getDisplacement() {
		return displacement;
	}

}