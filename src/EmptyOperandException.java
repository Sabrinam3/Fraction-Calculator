/*
 * Purpose: A custom exception class that checks for empty text fields and outputs a warning message
 * Coders: Sabrina Tessier and Andrew Moser
 * Date: Aug 6th, 2018
 */
import javax.swing.JOptionPane;

public class EmptyOperandException extends Exception
{
	public EmptyOperandException(String source) 
	{
		super();
		if(source.equals("numerator"))
		{
			JOptionPane.showMessageDialog(null, "Numerator field cannot be empty!","Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Denominator field cannot be empty!","Error", JOptionPane.WARNING_MESSAGE);
		}
	}//end constructor
}
//end class