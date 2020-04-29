/*
 * Purpose: A custom exception class that checks for a denominator of zero and outputs a warning message
 * Coders: Sabrina Tessier and Andrew Moser
 * Date: Aug 6th, 2018
 */
import javax.swing.JOptionPane;
public class DenominatorOfZeroException extends Exception
{
	public DenominatorOfZeroException()
	{
		JOptionPane.showMessageDialog(null, "Denominator cannot be zero!", "Error", JOptionPane.WARNING_MESSAGE);
	}
}
//end class