import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class creates the buttons and contains all the logic for the buttons function. 
 * all the logic for calculating new balance and ending the game is also done here. 
 * all error checking is also done here
 * @author zalakpatel
 *
 */
public class Gui extends JFrame
{
	
	//Defining constants 
	private static final double MAX_BET = 0.5; //max bet reduces 50 cents 
	private static final double MIN_BET = 0.1; //min bet reduces 10 cents 
	private static final double STANDARD_BET = 0.25; // spin bet reduces 25 cents 
	private static final int NUMBER_OF_SHAPES = 3; //Number of shapes 
	double balance = 1.0;
	
	ArrayList<SlotShape> shapes;
	JLabel balanceLabel;// the top part where welcome message and current balance is displayed in the frame
	JPanel mainPanel; //Main panel, complete window
	JPanel balancePanel; //Balance panel, will show text information about balance
	JPanel shapePanel; // Manages shapes 
	JPanel buttonPanel; //Manages all the buttons 
	JButton betMax; //Button called Bet Max
	JButton betMin;	//Button called Bet Min
	JButton spin;	//Button called spin
	
	Gui(){
		super("JSlotMachine Version 1.0");  // Title of the frame created 
		System.out.println("Creating Instance");
		try
		{
			System.out.println("Setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("Unable to set LookAndFeel");
		}
		
		int width = 600;
		int height = 300;
		mainPanel = new JPanel(); //Defining main layout 
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		balancePanel = new JPanel(); //balance panel and setting its layout
		balancePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		balanceLabel = new JLabel("Welcome to JSlotMachine 1.0");
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		balancePanel.add(balanceLabel);
		
		shapePanel = new JPanel(); // shapes panel, that contains the shapes 
		shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.X_AXIS));
		
		shapes = new ArrayList<SlotShape>(); //creating shapes and adding to panel
		shapePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		for (int i = 0 ; i < NUMBER_OF_SHAPES ; i++) 
		{
			SlotShape shape = new SlotShape(i);
			shapes.add(shape);
			shapePanel.add(shapes.get(i));
			if (i != NUMBER_OF_SHAPES - 1)
			{
				shapePanel.add(Box.createRigidArea(new Dimension(20, 0))); //adding some padding 
			}
			
		}
		shapePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		shapePanel.setPreferredSize(new Dimension(width, (height*8)/10));
		
		buttonPanel = new JPanel();
		
		//betMax button 
		betMax = new JButton("Bet Max");
		betMax.addActionListener(new ActionListener() 
		{ //click listeners for buttons
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("BET MAX Clicked.");
				spin(MAX_BET);
			}
		});
		
		//betMIN button
		betMin = new JButton("Bet Min");
		betMin.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("BET MIN Clicked.");
				spin(MIN_BET);
			}
		});
		
		//sping button
		spin = new JButton("Spin");
		spin.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("SPIN Clicked.");
				spin(STANDARD_BET);
			}
		});
		
		
		buttonPanel.add(betMax);
		buttonPanel.add(betMin);
		buttonPanel.add(spin);
		mainPanel.add(balancePanel);
		mainPanel.add(shapePanel);
		mainPanel.add(buttonPanel);
		
		add(mainPanel);
		setSize(width, height);  
		
	}
	
	/**
	 * This is all the logic for the buttons in the game
	 * if balance is 0 then game ends
	 * if balance is more than 0 then prints out the current balance on the GUI 
	 * if balance is 0 then prints out the message telling the user no moeny if left over
	 * @param bet
	 */
	protected void spin(double bet) { //function that spins 
		if (balance <= 0.0000001){
			return;
		}
		Set<Integer> typeSet = new HashSet<Integer>();
		Set<Integer> colorSet = new HashSet<Integer>();
		for (SlotShape shape : shapes){ //for all shapes create new random shapes 
			shape.random();
			typeSet.add(shape.getType()); //store type and color in a set
			colorSet.add(shape.getColor());
			shape.revalidate();
			shape.repaint();
		}
		if (typeSet.size() == 1 && colorSet.size() ==1){ // set contains unique values, so if all types and colors are same it will be 1
			balance = balance + bet;
		} else {
			balance = balance - bet;
		}
		
		if (balance > 0.0000001){ // handling precision error of double data type  
			balanceLabel.setText(String.format("Current Balance: $%.2f",balance));
		} else {
			balanceLabel.setText("You have no money left. Gave over.");
		}
	}
}
