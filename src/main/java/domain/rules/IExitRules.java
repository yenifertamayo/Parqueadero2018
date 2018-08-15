package domain.rules;

import model.Bill;

public interface IExitRules 
{
	double valueToPay(Bill bill);
}
