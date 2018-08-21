package domain.rules;

import java.util.Calendar;

import model.Vehicle;

public interface IIngressRules 
{
	void validateRule(Vehicle vehicle, Calendar ingressDate);
}
