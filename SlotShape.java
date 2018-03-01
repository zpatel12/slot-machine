import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 * SlotShape - This is the class where the rectangles and circles are defined
 * The colors for the shape are also set 
 * @author Zalak Patel 
 *
 */

public class SlotShape extends JPanel
{

	// Defining constants 
	final static int RECTANGLE = 1;
	final static int CIRCLE = 2;
	final static int RED = 3;
	final static int GREEN = 4;
	final static int BLUE = 5;
	Random randomNumber = new Random();
	int type;
	int color;
	int id;
	
	/**
	 * random shape (cirlce or rectangle) is generated here
	 * @param id
	 */
	public SlotShape(int id) 
	{
		super();
		this.id = id;
		random(); //generate random shape
	}
	
	@Override
	/**
	 * Sets up the grid for the slots and the size of the shapes and the color of the shape
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int h = getBounds().height;
		int w = getBounds().width;
		int x,y,r = 0;
		if (w > h)
		{
			r = h;
			x = ( w - h ) / 2;
			y = 0;
		} 
		else 
		{
			r = w;
			x = 0;
			y = ( h - w ) / 2;
		}
		if (this.color == RED)
		{
			g.setColor(new Color(255,0,0));
		} 
		else if (this.color == GREEN) 
		{
			g.setColor(new Color(0,128,0));
		} 
		else if (this.color == BLUE)
		{
			g.setColor(new Color(0,0,255));
		} 
		else
		{
			g.setColor(new Color(0,0,0));
		}
		if (this.type == RECTANGLE)
		{
			g.fillRect(x ,y, r, r);
		}
		else if (this.type == CIRCLE)
		{
			g.fillOval(x , y, r, r);
		}
		
	}
	
	/**
	 * Randomizes the color (red, blue, or green) 
	 * radomizes the type of shape (circle or rectangle) 
	 */
	public void random()
	{
		this.type = 1 + randomNumber.nextInt(2);
		this.color = 3 + randomNumber.nextInt(3);
	}
	
	/**
	 * getType - sets either a circle of rectangle 
	 * @return type
	 */
	public int getType() 
	{
		return type;
	}
	
	/**
	 * getColor - sets the color of each circle and rectangle
	 * @return color
	 */
	public int getColor() 
	{
		return color;
	}
	
}
