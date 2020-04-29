/*
 * Purpose: A custom exception class that checks for numbers that exceed the int max and outputs a warning message
 * Coders: Sabrina Tessier and Andrew Moser
 * Date: Aug 6th, 2018
 */
import javax.swing.JOptionPane;
public class LongOperandException extends Exception
{
	public LongOperandException()
	{
		JOptionPane.showMessageDialog(null, "Warning: operand entered exceeds int capacity", "Error", JOptionPane.WARNING_MESSAGE);
	}
}
//end class