package domain.rules;

import java.util.Calendar;

import model.Vehicle;

public interface IIngressRules 
{
	boolean validateRule(Vehicle vehicle, Calendar ingressDate);
}
