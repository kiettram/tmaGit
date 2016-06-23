package object1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawFactory extends JPanel {
	
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
			
			Ellipse2D p = new Ellipse2D.Double(x, y ,10 ,10);
			g2d.draw(p);
			
			g2d.setColor(Color.RED);
		    g2d.fill(p);  
		}
		
   }
	
}
