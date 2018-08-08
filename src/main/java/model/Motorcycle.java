package model;

public class Motorcycle extends Vehicle{

	public int displacement;
	
	public Motorcycle(String plate, int displacement) 
	{
		super(plate);
		this.displacement = displacement;
	}

	public int getDisplacement() {
		return displacement;
	}

}