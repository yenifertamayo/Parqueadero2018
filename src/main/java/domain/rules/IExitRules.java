package domain.rules;

import model.Bill;

public interface IExitRules 
{
	public double valueToPay(Bill bill);
}
