/**
 * This is the main class, the frame is drawn and the program runs from this class
 * @author Zalak Patel
 */
import javax.swing.JFrame;


public class PatelSlotMachine
{
	public static void main(String[] args) 
	{
		Gui gui = new Gui();  // Start GUI
		gui.setVisible(true);  // set window to visible
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
