package model;

public class Parking 
{
	public int maxCars;
	public int maxMotorcycles;
	public int maxDisplacementMotorcycle;
	public double surplusMotorcycle;
	public double valueHourCar;
	public double valueDayCar;
	public double valueHourMotorcycle;
	public double valueDayMotorcycle;
	
	public Parking(int maxCars, int maxMotorcycles, int maxDisplacementMotorcycle, double surplusMotorcycle,
			double valueHourCar, double valueDayCar, double valueHourMotorcycle, double valueDayMotorcycle) 
	{
		super();
		this.maxCars = maxCars;
		this.maxMotorcycles = maxMotorcycles;
		this.maxDisplacementMotorcycle = maxDisplacementMotorcycle;
		this.surplusMotorcycle = surplusMotorcycle;
		this.valueHourCar = valueHourCar;
		this.valueDayCar = valueDayCar;
		this.valueHourMotorcycle = valueHourMotorcycle;
		this.valueDayMotorcycle = valueDayMotorcycle;
	}

	public int getMaxCars() {
		return maxCars;
	}

	public int getMaxMotorcycles() {
		return maxMotorcycles;
	}

	public int getMaxDisplacementMotorcycle() {
		return maxDisplacementMotorcycle;
	}

	public double getSurplusMotorcycle() {
		return surplusMotorcycle;
	}

	public double getValueHourCar() {
		return valueHourCar;
	}

	public double getValueDayCar() {
		return valueDayCar;
	}


	public double getValueHourMotorcycle() {
		return valueHourMotorcycle;
	}


	public double getValueDayMotorcycle() {
		return valueDayMotorcycle;
	}
		
}