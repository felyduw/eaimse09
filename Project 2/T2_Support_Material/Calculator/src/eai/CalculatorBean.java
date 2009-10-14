package eai;

import javax.ejb.Stateless;

@Stateless
public class CalculatorBean 
	implements Calculator 
{

	public CalculatorBean() 
    {
    }

	public double add(double a, double b) 
	{
		return a+b;
	}
}

