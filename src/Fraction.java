
/**
 * Program name:Fraction.java
 * Purpose:This class provides a variety of methods that will be used to manipulate
 * 				 the fractions that the user enters into the application. Operations
 * 				 include addition, multiplication, reducing to lowest terms, and converting
 * 				 to decimal
 * Coder: Sabrina Tessier and Andrew Moser
 * Date:Started: Jul 25, 2018 Completed: Aug 9, 2018
 */

public class Fraction implements Comparable<Fraction>
{
	private int numerator;
	private int denominator;
	
	// Default Constructor
	public Fraction() 
	{
		this.numerator = 1;
		this.denominator = 1;
	}
	
	//Parameter-specified Constructor
	public Fraction(int num, int den) 
	{
		this.numerator = num;
		this.denominator = den;
	}
	
	//GETTERS AND SETTERS
	public int getNumerator()
	{
		return numerator;
	}

	public void setNumerator(int numerator)
	{
		this.numerator = numerator;
	}

	public int getDenominator()
	{
		return denominator;
	}

	public void setDenominator(int denominator)
	{
		this.denominator = denominator;
	}

	/*
	 * Method Name: convertToDecimal
	 * Purpose: Converts a fraction to decimal form
	 * Accepts: Nothing
	 * Returns: a double(the fraction as a decimal)
	 */
	
	public double convertToDecimal()
	{
		return (double)numerator/denominator;
	}
	
	/*
	 * Method Name: convertToReciprocal
	 * Purpose: Converts a fraction to its reciprocal
	 * Accepts: Nothing
	 * Returns: a Fraction object that is the reciprocal
	 */
	public Fraction convertToReciprocal()
	{
		return new Fraction(this.denominator, this.numerator);
	}
	
	/*
	 * Method Name: add
	 * Purpose: Adds two fractions together
	 * Accepts: A Fraction object
	 * Returns: A new Fraction that is the two fractions added
	 */
	public Fraction add(Fraction f) {
		int newFractionNum = (this.numerator * f.denominator) + (this.denominator * f.numerator);
		int newFractionDen = this.denominator * f.denominator;
		return new Fraction(newFractionNum, newFractionDen);
		//TODO: reduce to lowest terms??
		
	}
	
	/*
	 * Method Name: multiply
	 * Purpose: Multiplies two fractions together
	 * Accepts: A Fraction object
	 * Returns: A Fraction object that is the two fractions multiplied
	 */
	public Fraction multiply(Fraction f)
	{
		return new Fraction(this.numerator * f.numerator, this.denominator * f.denominator);
		//TODO: reduce to lowest terms?
	}
	
	/*
	 * Method Name: lowestTerms
	 * Purpose: Reduces a Fraction to lowest terms 
	 * Accepts: Nothing
	 * Returns: A Fraction object which is the original Fraction in lowest terms
	 */
	public Fraction lowestTerms()
	{
		int num = this.numerator;
		int den = this.denominator;
		
		//Loop until a value is found that can divide evenly into both numerator and denominator
		for(int i = num; i >= 2; i--)
		{
			if(num % i == 0 && den % i == 0)
			{
				return new Fraction(num / i, den / i);
			}//end if
		}//end for
		return new Fraction(num, den);
	}//end lowestTerms
	
	/*
	 * Method Name: equals
	 * Purpose:determines if two fractions are equal
	 * Accepts:a Fraction object
	 * Returns:returns a boolean
	 */
	public boolean equals(Fraction f)
	{
		if(this.compareTo(f) == 0)
			return true;
		else
			return false;
	}//end equals
	
	/*
	 * Method Name:greaterThan
	 * Purpose:determines if first fraction is greater than second
	 * Accepts:a Fraction object
	 * Returns:returns a boolean
	 */
	public boolean greaterThan(Fraction f)
	{
		if(this.compareTo(f) == 1)
			return true;
		else
			return false;
	}
	/*
	 * Method Name: toString
	 * Purpose:print out the fraction
	 * Accepts:nothing
	 * Returns:a string with the Fraction's values
	 */
	public String toString()
	{
		return "The fraction is " + this.numerator + "/" + this.denominator;
	} 
	/*
	 * Method Name: compareTo
	 * Purpose: returns an integer value denoting whether the first fraction is greater than, equal to, or less than (respectively: 1, 0, -1)
	 * Accepts:a Fraction object
	 * Returns:an integer indicating whether the first fraction is greater than, equal to, or less than
	 */
	@Override
	public int compareTo(Fraction f)
	{
		double firstF = this.convertToDecimal();
		double secondF = f.convertToDecimal();
	  if (firstF > secondF)
	  	return 1;
	  else if (firstF == secondF)
	  	return 0;
	  else
	  	return -1;
	}//end compareTo
	
}
//end class