package object1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/*
 * 1. Draw points by add those into a list and draw each one 
 */
public class DrawPoints extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private List<Double> xList = new ArrayList<Double>();
	private List<Double> yList= new ArrayList<Double>();
	
	public void addX(double x) {
		xList.add(x);
	}
	
	public void addY(double y) {
		yList.add(y);
	}
	
	@Override
   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
				
		for (int i = 0; i < xList.size(); i++) {
			double x = xList.get(i);
			double y = yList.get(i);
			
			// draw point here
			Ellipse2D p = new Ellipse2D.Double(x, y ,10 ,10); // create a new ellipse with specific x and y coordinates
			g2d.draw(p); // draw ellipse
			
			g2d.setColor(Color.RED); // set color 
		    g2d.fill(p); // fill ellipse 
		}		
   }	
}
