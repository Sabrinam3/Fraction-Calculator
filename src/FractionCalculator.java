/**
 * Program name:FractionCalculator.java
 * Purpose: Creates a fraction calculator that also provides input validation and error checking
 * Coders: Sabrina Tessier and Andrew Moser
 * Date: Started: Jul 25, 2018 Finished: Aug 9th, 2018
 */
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class FractionCalculator extends JFrame
{
	protected ArrayList<Fraction> fractionList;
	
	//COMPONENTS
	private JTextField numeratorBox;
	private JTextField denominatorBox;
	private JTextArea fractionBox;
	private JTextArea outputBox;
	private JComboBox<String> operationBox;
	

	//FRAME PROPERTIES
	private static String TITLE = "Fraction Calculator";
	private final int FRAME_WIDTH = 900;
	private final int FRAME_HEIGHT = 500;
	
//COLORS
private Color areaBackgroundColor = new Color(225,248,254);
private Color panelBackgroundColor = new Color(182,228,244);
private Color fieldBackgroundColor = new Color(255,254,239);

//FONTS
private Font titleFont = new Font("Arial", Font.BOLD,18);
private Font fractionFont = new Font("Yu Gothic", Font.PLAIN, 20);
private Font inputFont = new Font("Yu Gothic", Font.BOLD, 16);

//BORDER FOR ALL THE PANELS
Border titleBorder = BorderFactory.createLineBorder(Color.BLACK);
	
	//CONSTRUCTOR
	public FractionCalculator()
	{
		//SET THE TITLE BY CALLING SUPER
		super(TITLE);
		
		//INSTANTIATE THE ARRAYLIST TO HOLD THE FRACTIONS
		fractionList = new ArrayList<Fraction>();
		
		
		//SET FRAME PROPERTIES
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new GridLayout(1,4, 2,0));
		setLocationRelativeTo(null);
		setResizable(false);
		
		//ADD MAIN PANELS
		add(createOuterLeftPanel());
		add(createInnerLeftPanel());
		add(createInnerRightPanel());
		add(createOuterRightPanel());
		
		//ADD OUR MENU BAR
		this.setJMenuBar(createMenuBar());
	
		//SET DEFAULT CLOSE OPERATION
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//SET VISIBLE
		setVisible(true);
	}//end constructor
	
	/*
	 * Method Name:createMenuBar
	 * Purpose: Creates the JMenuBar for this application
	 * Accepts:Nothing
	 * Returns:the completed menu bar
	 */
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// CREATE TWO MAIN MENUS - OPERATIONS WHICH WILL LIST THE FRACTION OPERATIONS AND ABOUT WHICH WILL SHOW THE CODERS' NAMES
		JMenu fractionOperations = new JMenu("Operations");
		JMenu aboutCoders = new JMenu("About");
		
		//OPERATIONS FOR THE OPERATIONS MENU
		JMenuItem dec = new JMenuItem("Decimal");
		JMenuItem rec = new JMenuItem("Reciprocal");
		JMenuItem add = new JMenuItem("Fraction1 + Fraction2");
		JMenuItem mul = new JMenuItem("Fraction1 * Fraction2");
		JMenuItem eq = new JMenuItem("Is Fraction1 = Fraction2");
		JMenuItem gr = new JMenuItem("Is Fraction1 > Fraction2");
		JMenuItem low = new JMenuItem("Lowest Terms");
		JMenuItem sort = new JMenuItem("Sort List");
		
		//MENU ITEM FOR THE ABOUT MENU
		aboutCoders.add(new JMenuItem("Developers: Sabrina Tessier & Andrew Moser"));
		
		//ADD THE MENU ITEMS TO THE OPERATIONS MENU
		fractionOperations.add(dec);
		fractionOperations.add(rec);
		fractionOperations.add(add);
		fractionOperations.add(mul);
		fractionOperations.add(eq);
		fractionOperations.add(gr);
		fractionOperations.add(low);
		fractionOperations.add(sort);
		
		//ADD OUR ACTION LISTENER SO THAT THE MENU OPTIONS CAN ALSO BE USED TO PROCESS FRACTIONS
		comboListener menuOperationListener = new comboListener();
		dec.addActionListener(menuOperationListener);
		rec.addActionListener(menuOperationListener);
		add.addActionListener(menuOperationListener);
		mul.addActionListener(menuOperationListener);
		eq.addActionListener(menuOperationListener);
		gr.addActionListener(menuOperationListener);
		low.addActionListener(menuOperationListener);
		sort.addActionListener(menuOperationListener);
	
		//CREATE SOME HOT(and spicy) KEY COMBINATIONS
		fractionOperations.setMnemonic('O');
		aboutCoders.setMnemonic('A');
		dec.setAccelerator(KeyStroke.getKeyStroke('D', ActionEvent.ALT_MASK));
		rec.setAccelerator(KeyStroke.getKeyStroke('R', ActionEvent.ALT_MASK));
		add.setAccelerator(KeyStroke.getKeyStroke('A',ActionEvent.CTRL_MASK|ActionEvent.ALT_MASK));
		mul.setAccelerator(KeyStroke.getKeyStroke('M', ActionEvent.ALT_MASK));
		eq.setAccelerator(KeyStroke.getKeyStroke('E', ActionEvent.ALT_MASK));
		gr.setAccelerator(KeyStroke.getKeyStroke('G', ActionEvent.ALT_MASK));
		low.setAccelerator(KeyStroke.getKeyStroke('L', ActionEvent.ALT_MASK));
		sort.setAccelerator(KeyStroke.getKeyStroke('S', ActionEvent.ALT_MASK));
		
		//ADD MENUS TO MENU BAR
		menuBar.add(fractionOperations);
		menuBar.add(aboutCoders);	
		
		//RETURN COMPLETED JMENUBAR
		return menuBar;
	}//end createMenuBar
	
	/*
	 * Method Name:createOuterLeftPanel
	 * Purpose: Creates the outer left panel which has the text boxes and buttons
	 * Accepts:Nothing
	 * Returns:the completed panel
	 */
	public JPanel createOuterLeftPanel()
	{
		final int FIELD_WIDTH = 220;
		
		//CONSTRUCT PANEL AND SET LAYOUT
		JPanel outerLeftPanel = new JPanel();
		outerLeftPanel.setLayout(new BoxLayout(outerLeftPanel, BoxLayout.Y_AXIS));
		outerLeftPanel.setBackground(panelBackgroundColor);
		
		//CONSTRUCT AND SET BORDER
		TitledBorder inputBorder = new TitledBorder(titleBorder,"Enter a fraction: ", TitledBorder.DEFAULT_JUSTIFICATION, 
																								TitledBorder.ABOVE_TOP, titleFont, Color.DARK_GRAY);
		outerLeftPanel.setBorder(inputBorder);
		
		// INITIALIZE COMPONENTS(JLabels and JTextFields) AND SET SOME VISUAL PROPERTIES
		JLabel numLabel = new JLabel("Numerator: ");
		JLabel denLabel = new JLabel("Denominator: ");
		numeratorBox = new JTextField(FIELD_WIDTH);
		denominatorBox = new JTextField(FIELD_WIDTH);
		
		//SET FONT FOR LABELS AND TEXT FIELDS
		numLabel.setFont(fractionFont);
		denLabel.setFont(fractionFont);
		denominatorBox.setFont(inputFont);
		numeratorBox.setFont(inputFont);
		
		//SET BACKGROUND COLORS
		numeratorBox.setBackground(fieldBackgroundColor);
		denominatorBox.setBackground(fieldBackgroundColor);
		
		//SET BORDERS
		numeratorBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		denominatorBox.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		//CONSTRUCT BUTTON
		JButton buildFraction = new JButton("Build Fraction");
		
		//KEYBOARD SHORTCUT FOR BUTTON
		buildFraction.setMnemonic(KeyEvent.VK_B);
		
		//BACKGROUND COLOR
		buildFraction.setBackground(areaBackgroundColor);
		
		//FONT 
		buildFraction.setFont(titleFont);
		
		//CURSOR
		Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		buildFraction.setCursor(handCursor);
	
		//ACTION LISTENER
		buildFraction.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent event)
			{
				//INTEGERS TO HOLD NUM/DEN AFTER PARSING
				int num, den;
				
				//TEMPORARILY STORE THE VALUES FROM TEXT BOXES IN STRING VARIABLES
				String numerator = numeratorBox.getText();
				String denominator = denominatorBox.getText();
				
				//TELLS EXCEPTION CLASSES WHICH TEXT BOX(NUM/DEN) CAUSED THE ERROR
				String boxSource;
				
				//TRY BLOCK FOR NUMERATOR FIELD
				try {
					if(numerator.equals("")) //EMPTY TEXT BOX
					{
						boxSource = "numerator";
						throw new EmptyOperandException(boxSource);
					}
					else if(Long.parseLong(numerator) > Integer.MAX_VALUE) // GREATER THAN INT MAX
					{
						throw new LongOperandException();
					}
					else
					{
						//ALL POSSIBLE ERRORS HAVE BEEN CAUGHT, SO THE VALUE CAN BE SAFELY PARSED TO AN INTEGER AND STORED IN NUM
						num = Integer.parseInt(numerator);
					}
					
					//TRY BLOCK FOR DENOMINATOR FIELD
					try
					{
						if(denominator.equals("")) //EMPTY TEXT BOX
						{
							boxSource = "denominator";
							throw new EmptyOperandException(boxSource);
						}
						else if(Long.parseLong(denominator) > Integer.MAX_VALUE) // GREATER THAN INT MAX
						{
							throw new LongOperandException();
						}
						else
						{
							den = Integer.parseInt(denominator); //CAST TO AN INTEGER
							if(den == 0) //CHECK IF DENOMINATOR IS ZERO
							{
								throw new DenominatorOfZeroException();
							}
							
							//ERRORS HAVE BEEN CAUGHT, NOW A FRACTION OBJECT CAN BE CONSTRUCTED
							Fraction f = new Fraction(num, den);
							
							//ADD FRACTION TO ARRAYLIST
							fractionList.add(f);
							
							//DISPLAY FRACTION(S) IN FRACTION AREA
							fractionBox.append(num + "/" + den + "\n");
							
							//CLEAR THE TEXT BOXES SO THAT USER CAN ENTER ANOTHER FRACTION AND RETURN FOCUS TO NUMERATOR BOX FOR EASE OF USE
							numeratorBox.setText("");
							denominatorBox.setText("");
							numeratorBox.requestFocus();
						}//end else	
					}//end try
					//CATCH BLOCKS FOR DENOMINATOR FIELD
					catch(LongOperandException e)
					{
						denominatorBox.setText("");
						denominatorBox.requestFocus();
					}
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Denominator must be an integer!", "Error", JOptionPane.WARNING_MESSAGE);
						
						//CLEAR OUT THE BAD INPUT
						denominatorBox.setText("");
						
						//RETURN FOCUS TO DENOMINATOR BOX
						denominatorBox.requestFocus();
					}//end catch
					catch(EmptyOperandException e)
					{
						denominatorBox.requestFocus();
					}//end catch
					catch(DenominatorOfZeroException e)
					{
						denominatorBox.requestFocus();
					}
				//CATCH BLOCKS FOR NUMERATOR FIELD
				}catch(LongOperandException e)
				{
					numeratorBox.setText("");
					numeratorBox.requestFocus();
				}
				catch(NumberFormatException  e)
				{
					JOptionPane.showMessageDialog(null, "Numerator must be an integer!", "Error", JOptionPane.WARNING_MESSAGE);
					numeratorBox.setText("");
					numeratorBox.requestFocus();
				}//end catch
				catch (EmptyOperandException e)
				{
					numeratorBox.requestFocus();
				}//end catcH
			}//end actionPerformed
		});//end add action listener
		
		//INITIALIZE START OVER BUTTON
		JButton startOver = new JButton("Start Over!");
		
		//KEYBOARD SHORTCUT FOR BUTTON
		startOver.setMnemonic(KeyEvent.VK_S);
		//BACKGROUND COLOR
		startOver.setBackground(areaBackgroundColor);
		
		//FONT
		startOver.setFont(titleFont);
		
		//CURSOR
		startOver.setCursor(handCursor);
		
		//ACTION LISTENER USING ANONYMOUS INNER CLASS
		startOver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event)
			{
				//CLEAR ALL GUI COMPONENTS AND CLASS VARIABLES
				fractionList.clear();
				numeratorBox.setText("");
				denominatorBox.setText("");
				fractionBox.setText("");
				outputBox.setText("");
			}//end actionPerformed			
		});//end addActionListener
		
	//CREATE DIMENSIONS FOR THE FILLER OBJECTS THAT WILL BE USED TO SPACE OUT COMPONENTS
		Dimension minSize = new Dimension(0, 200);
		Dimension prefSize = new Dimension(0,200);
		Dimension maxSize = new Dimension(0,250);
		Dimension minSize1 = new Dimension(0,10);
		Dimension prefSize1 = new Dimension(0,15);
		Dimension maxSize1 = new Dimension(0,10);
		Dimension minSize2 = new Dimension(0,30);
		Dimension prefSize2 = new Dimension(0,30);
		Dimension maxSize2 = new Dimension(0,30);
		
		//ALIGN AND POSITION COMPONENTS WITHIN THE PANEL, USING DIMENSIONS AS FILLER
		numLabel.setAlignmentX(CENTER_ALIGNMENT);
		outerLeftPanel.add(numLabel);
		outerLeftPanel.add(numeratorBox);
		outerLeftPanel.add(new Box.Filler(minSize1, prefSize1, maxSize1));
		denLabel.setAlignmentX(CENTER_ALIGNMENT);
		outerLeftPanel.add(denLabel);
		outerLeftPanel.add(denominatorBox);
		outerLeftPanel.add(new Box.Filler(minSize2, prefSize2, maxSize2));
		buildFraction.setAlignmentX(CENTER_ALIGNMENT);
		outerLeftPanel.add(buildFraction);
		outerLeftPanel.add(new Box.Filler(minSize1, prefSize1, maxSize1));
		startOver.setAlignmentX(CENTER_ALIGNMENT);
		outerLeftPanel.add(startOver);
		outerLeftPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		
		//RETURN THE COMPLETED PANEL
		return outerLeftPanel;
	}//end createOuterLeftPanel
	
	/*
	 * Method Name:createInnerLeftPanel
	 * Purpose: Creates the inner left panel which has the fraction area
	 * Accepts:Nothing
	 * Returns:the completed panel
	 */
	public JPanel createInnerLeftPanel()
	{
		//INITIALIZE COMPONENTS
		JPanel innerLeftPanel = new JPanel();
		fractionBox = new JTextArea();
		
		//LAYOUT OF PANEL
		innerLeftPanel.setLayout(new GridLayout(1,1));
		
		//SET BACKGROUND COLORS
		innerLeftPanel.setBackground(panelBackgroundColor);
		fractionBox.setBackground(areaBackgroundColor);
		
		//FONT
		fractionBox.setFont(fractionFont);
		
		//CONSTRUCT AND SET BORDER
		TitledBorder inputBorder = new TitledBorder(titleBorder,"Here is your fraction: ", TitledBorder.DEFAULT_JUSTIFICATION, 
																								TitledBorder.ABOVE_TOP, titleFont, Color.DARK_GRAY);
		innerLeftPanel.setBorder(inputBorder);
		fractionBox.setEditable(false); //USERS CANNOT EDIT FRACTIONS FROM THE BOX
		
		//SCROLL PROPERTY THAT WILL ALLOW USER TO SCROLL THROUGH LIST OF FRACTIONS IF IT EXCEEDS HEIGHT OF THE BOX
		JScrollPane fractionPane = new JScrollPane(fractionBox);
		
		//ADD SCROLL PANE TO PANEL
		innerLeftPanel.add(fractionPane);
		
		//RETURN THE COMPLETED PANEL
		return innerLeftPanel;
	}//end createInnerLeftPanel
	
	/*
	 * Method Name: createInnerRightPanel
	 * Purpose: Creates the inner right panel which has the operations combo box
	 * Accepts: Nothing
	 * Returns: the completed panel
	 */
	
	public JPanel createInnerRightPanel()
	{
		//CONSTRUCT PANEL
		JPanel innerRightPanel = new JPanel();
		
		//LAYOUT
		innerRightPanel.setLayout(new BorderLayout());
		
		//BACKGROUND COLOR
		
		innerRightPanel.setBackground(panelBackgroundColor);
		//CONSTRUCT AND SET BORDER  
		TitledBorder operationBorder = new TitledBorder(titleBorder,"Select an Operation:", TitledBorder.DEFAULT_JUSTIFICATION, 
																										TitledBorder.ABOVE_TOP, titleFont, Color.DARK_GRAY);
		
		innerRightPanel.setBorder(operationBorder);
		
		//CREATE THE OPERATION COMBO BOX
		CreateComboBox();
		
		//ADD THE COMBO BOX TO THE FRAME
		innerRightPanel.add(operationBox, BorderLayout.NORTH);
		
		//RETURN THE PANEL
		return innerRightPanel;
	}//end createInnerRightPanel
	
	/*
	 * Method Name: createOuterRightPanel
	 * Purpose: Creates the outer right panel	which contains the operation result area
	 * Accepts: Nothing
	 * Returns: the completed panel
	 */
	public JPanel createOuterRightPanel()
	{
		//CONSTRUCT PANEL 
		JPanel outerRightPanel = new JPanel();
		
		//INITIALIZE COMPONENTS
		outputBox = new JTextArea();
		
		//LAYOUT
		outerRightPanel.setLayout(new GridLayout(1,1));
		
		//SET BACKGROUND COLOR
		outerRightPanel.setBackground(panelBackgroundColor);
		outputBox.setBackground(areaBackgroundColor);
		
		//FONT
		outputBox.setFont(fractionFont);
		
		//CONSTRUCT AND SET BORDER 
		TitledBorder resultBorder = new TitledBorder(titleBorder,"Here is your operation:", TitledBorder.DEFAULT_JUSTIFICATION, 
																								 TitledBorder.ABOVE_TOP, titleFont, Color.DARK_GRAY);
		outerRightPanel.setBorder(resultBorder);

		
		outputBox.setEditable(false);//WE DON'T WANT USERS TO BE ABLE TO EDIT THIS BOX
		
		//SCROLL PANE IN CASE THE OUTPUT AREA GETS REALLY LONG
		JScrollPane outputPane = new JScrollPane(outputBox);

		//WORD WRAP
		outputBox.setLineWrap(true);
		
		//ADD TEXT AREA TO PANEL
		outerRightPanel.add(outputPane);
		
		//RETURN THE PANEL
		return outerRightPanel;
	}//createOuterRightPanel
		
	/*
	 * Method Name: createComboBox
	 * Purpose: Constructs a combo box which will be used to
	 * 					hold the various operations that can be done
	 * 					on the fraction
	 * Accepts: Nothing
	 * Returns: void
	 */
	public void CreateComboBox()
	{
		//ARRAY TO HOLD THE OPERATIONS
		String[] operationList = {"Decimal", "Reciprocal", "Lowest Terms", "Fraction1 + Fraction2", 
				"Fraction1 * Fraction2","Is Fraction1 = Fraction2","Is Fraction1 > Fraction2", "Sort List"};
		
		//INITIALIZE THE COMBO BOX USING THE OPERATION LIST
		operationBox = new JComboBox<String>(operationList);
		
		//BACKGROUND COLOR
		operationBox.setBackground(fieldBackgroundColor);
		
		//FONT
		operationBox.setFont(inputFont);
		
		//CREATE INSTANCE OF COMBOLISTENER AND ATTACH TO THE COMBO BOX
		comboListener operationListener = new comboListener();
		operationBox.addActionListener(operationListener);
	}//end createComboBox
	
	/*
	 * Class Name: comboListener
	 * Purpose: listens for which operation is being called and performs validation that the operation can be completed,
	 * 					given the current size of the array list
	 */
	public class comboListener implements ActionListener
	{
		//WILL HOLD THE SOURCE (THE MENU ITEM/COMBO BOX ITEM) BEING CHOSEN
		private String operation;
		
		public void actionPerformed(ActionEvent e)
		{
			 
			//TRY TO CAST THE SOURCE OF THIS ACTION TO A JMENUITEM. IF THIS THROWS AN EXCEPTION, THEN THE ACTION IS COMING FROM THE COMBO BOX
				try {
					JMenuItem menuItem = (JMenuItem)e.getSource();
					//FIND OUT WHICH MENU ITEM IS BEING SELECTED AND STORE IT INTO THE OPERATION STRING
					operation = menuItem.getText();
				
				}catch(ClassCastException error)
				{
					//FIND OUT WHICH COMBO BOX ITEM IS BEING SELECTED AND STORE IT IN THE OPERATION STRING
					operation = (String) operationBox.getSelectedItem();
					//RETURN FOCUS TO THE FIRST ITEM IN THE COMBO BOX
						operationBox.setSelectedIndex(0);
				}//end catch
				 
				//CLEAR OUT THE LAST OPERATION
				outputBox.setText("");
				
				//DETERMINE IF THE OPERATION SELECTED IS A UNARY OR BINARY OPERATION. THEN, CALL VALIDATION METHODS WHICH WILL VERIFY THE FRACTION LIST 
				//CONTAINS ENOUGH FRACTIONS TO PERFORM THE OPERATION
				
				//UNARY OPERATIONS
				if(operation.equals("Decimal") || operation.equals("Reciprocal"))
				{
					validateUnaryOperation(operation);
				}
				//BINARY OPERATIONS
				else if(operation.equals("Fraction1 + Fraction2") || operation.equals("Fraction1 * Fraction2") || operation.equals("Is Fraction1 = Fraction2") || operation.equals("Is Fraction1 > Fraction2"))
				{
					validateBinaryOperation(operation);				
				}//end else if
				
				//SORT LIST CAN BE DONE ON ONE OR MULTIPLE FRACTIONS
				else if(operation.equals("Sort List"))
				{
					//Make sure there is at least 1 fraction in the list
					if(fractionList.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You must enter at least one fraction", "Error", JOptionPane.WARNING_MESSAGE);
						numeratorBox.requestFocus();
					}
					else
					{
						//Sort the list
						quickSort(fractionList, 0, fractionList.size()-1);
						//Display the sorted list
						printList(fractionList);
					}//end else
				}//end else if
				//LOWEST TERMS CAN BE DONE ON ONE OR MULTIPLE FRACTIONS
				else if(operation.equals("Lowest Terms"))
				{
					if(fractionList.isEmpty())
					{
						//DISPLAY ERROR MESSAGE
						JOptionPane.showMessageDialog(null, "You must enter at least one fraction", "Error", JOptionPane.WARNING_MESSAGE);
						numeratorBox.requestFocus();
					}
					else
					{
						//CONVERT ALL FRACTIONS IN LIST TO LOWEST TERMS
						lowestTerms(fractionList);
						printList(fractionList);
					}
				}//end else if
		}//end actionPerformed
	}//end comboListener
	
	/*
	 * Method Name: validateUnaryOperation
	 * Purpose: validates that the fraction list has at least 1 fraction in it
	 * Accepts: a string representing the operation to be validated
	 * Returns:nothing
	 */
	public void validateUnaryOperation(String operation)
	{
		if(fractionList.size() == 1)
		{
			//PROCESS THE OPERATION WITH THE FRACTION IN THE LIST
			processUnary(fractionList.get(0), operation);
		}
		else if(fractionList.size() > 1)
		{
			//PROCESS THE OPERATION WITH THE LAST FRACTION ENTERED
			processUnary(fractionList.get(fractionList.size()-1), operation);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must enter at least one fraction!", "Error", JOptionPane.WARNING_MESSAGE);
			numeratorBox.requestFocus();
		}
	}//end validateUnaryOperation
	
	/*
	 * Method Name: validateBinaryOperation
	 * Purpose: validates that the fraction list has at least 2 fractions in it
	 * Accepts: a string representing the operation to be validated
	 * Returns:nothing
	 */
	public void validateBinaryOperation(String operation)
	{
		if(fractionList.size() == 2)
		{
			//PROCESS THE OPERATION WITH THE 2 FRACTIONS IN THE LIST
			processBinary(fractionList.get(0), fractionList.get(1), operation);
		}
		else if(fractionList.size() > 2)
		{
			//PROCESS THE OPERATION WITH THE LAST 2 FRACTIONS ENTERED
			processBinary(fractionList.get(fractionList.size()-2), fractionList.get(fractionList.size()-1), operation);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must enter at least two fractions!", "Error", JOptionPane.WARNING_MESSAGE);
			numeratorBox.requestFocus();
		}
	}//end validateBinaryOperation
	
	/*
	 * Method Name: lowestTerms
	 * Purpose: Converts all fractions in the array list to lowest terms and displays them in the output area
	 * Accepts: An arrayList of fractions
	 * Returns: void
	 */
	void lowestTerms(ArrayList<Fraction> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			//SET EACH FRACTION IN THE LIST TO THE LOWEST TERMS
			list.set(i, list.get(i).lowestTerms());
		}
	}//end lowestTerms
	
	/*
	 * Method Name: processUnary
	 * Purpose: Performs calculations on a fraction and outputs the results to the result box
	 * Accepts: A fraction object, a string representing the operation to be performed
	 * Returns: nothing
	 */
	public void processUnary(Fraction f, String operation)
	{
		//RECEIVES THE RETURN OF THE OPERATION(APPLIES TO RECIPROCAL ONLY)
		Fraction f1;
		
		if(operation.equals("Decimal"))
		{
			outputBox.setText(f.getNumerator() + "/" + f.getDenominator() + " is " + f.convertToDecimal());
		}
		else if(operation.equals("Reciprocal"))
		{
			f1 = f.convertToReciprocal();
			outputBox.setText(f.getNumerator() + "/" + f.getDenominator() + " and " + f1.getNumerator() + "/" + f1.getDenominator());
		}
	}//end processUnary
	
	/*
	 * Method Name : processBinary
	 * Purpose: Performs calculations on two fractions and outputs the results to the result box
	 * Accepts: 2 fraction objects, a string representing the operation to be performed
	 * Returns:nothing
	 */
	public void processBinary(Fraction f1, Fraction f2, String operation)
	{
		 //RECEIVES THE RETURN OF THE OPERATION
			Fraction f3;
			
			if(operation.equals("Fraction1 + Fraction2"))
			{
				f3 = f1.add(f2);
				outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " + " +  f2.getNumerator() + "/"
						+ f2.getDenominator() + "= " + f3.getNumerator() + "/" + f3.getDenominator());
			}//end if
			
			else if(operation.equals("Fraction1 * Fraction2"))
			{
				f3 = f1.multiply(f2);
				outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " * " +  f2.getNumerator() + "/"
						+ f2.getDenominator() + "= " + f3.getNumerator() + "/" + f3.getDenominator());
			}//end else if
			
			else if(operation.equals("Is Fraction1 = Fraction2"))
			{
				if(f1.equals(f2))
					outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " is equal to " + f2.getNumerator() + "/" + f2.getDenominator());
				else
					outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " is NOT equal to " + f2.getNumerator() + "/" + f2.getDenominator());
			}//end else if
			
			else if(operation.equals("Is Fraction1 > Fraction2"))
			{
				if(f1.greaterThan(f2))
					outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " is greater than " + f2.getNumerator() + "/" + f2.getDenominator());
				else
					outputBox.setText(f1.getNumerator() + "/" + f1.getDenominator() + " is NOT greater than " + f2.getNumerator() + "/" + f2.getDenominator());
			}//end else if
	}//end processBinary
	
	/*
	 * Method Name: sortFractionList
	 * Purpose: Sorts the list of fractions by implementing a quicksort algorithm 
	 * 					customized for fractions
	 * Accepts: an arrayList of fractions, int low(the starting point of the sort),
	 * 					int high(the ending point of the sort)
	 * Returns:nothing
	 */
	
	public void quickSort(ArrayList<Fraction> fracList, int low, int high)
	{
		// GET THE MIDPOINT
		int mid = (low + high) / 2;
		
		//TEMP VARIABLES TO MOVE AROUND
		int i = low;
		int j = high;
		
		//PIVOT VALUE 
		Fraction pivot = fracList.get(mid);
		
		//LOOP UNTIL I IS GREATER THAN J
		while (i <= j)
		{
			//MOVE I OVER UNTIL IT'S GREATER THAN PIVOT
			while(fracList.get(i).compareTo(pivot) == -1)
			{
				i++;
			} // end while
			
			// SHUFFLE J OVER UNTIL ITS LESS THAN PIVOT
			while(fracList.get(j).compareTo(pivot) == 1)
			{
				j--;
			} // end while
			
			if (i <= j)
			{
				//SWAP THE FRACTIONS
				Fraction temp = fracList.get(i);
				fracList.set(i, fracList.get(j));
				fracList.set(j, temp);
				i++;
				j--;
			} // end if
		} // end while
		
		// RECURSIVE CALL TO QUICKSORT WITH NEW BEGINNING AND END POINTS
		if (low < j)
		{
			quickSort(fracList, low, j);
		}
		if (i < high)
		{
			quickSort(fracList, i, high);
		}
	} // end quicksort
	
	/*
	 * Method Name: printSortedList
	 * Purpose: outputs the sorted fraction list to the output box/fraction area
	 * Accepts: an arrayList of Fraction objects to print
	 * Returns: void
	 */
	public void printList(ArrayList<Fraction> list)
	{
		fractionBox.setText("");
		for(Fraction a : list)
		{
			outputBox.append(a.getNumerator() + "/" + a.getDenominator() + "\n");
			fractionBox.append(a.getNumerator() + "/" + a.getDenominator() + "\n");
		}
	}//end printSortedList

	public static void main(String[] args)
	{
		FractionCalculator frame = new FractionCalculator();
	}
	//end main
}
//end class