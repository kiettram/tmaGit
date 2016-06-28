package object1;

import javax.swing.JFrame;

/*
 * 1. Draw points
 * 2. Draw color of specific point
 */

public class DrawChickenTask implements Task {

	double x; // x-axis (horizontally to the right)
    double y; // y-axis (vertically going down)
    
    JFrame frame = new JFrame();    
    
    
    
    
//    DrawPoints drawPoints;
    
    
    
    
    
    DrawChickenTask() {
//    	frame.setTitle("Draw chicken task frame");
////    	frame.setBackground(Color.LIGHT_GRAY);
//    	frame.setSize(2000, 1100);
//    	frame.setLocationRelativeTo(null);
//    	frame.setVisible(true);
//    	    	   
//    	drawPoints = new DrawPoints(); 
//    	frame.add(drawPoints);
//    	drawPoints.repaint();
//    	Preparation.addToGridPanel(panel);
	}
	
	public void update(Chicken chicken) {
		
		System.out.println("Draw chicken task UPDATE method was called "); // sign to award DrawChickenTask successful was called 

		// get coordinates of one point
		x = chicken.getCoordinates().getX();
		y = chicken.getCoordinates().getY();

		
		// show x and y
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		frame.setTitle("Frame test");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(2000, 1100);
		
		DrawPoints dr = new DrawPoints();
		frame.add(dr);
		dr.repaint();
				
			
//			dr.addX(x);
//			dr.addY(y);
//			
//			
//			dr.repaint();
//			
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}    
		
		
		
		
			for (int i = 0 ; i <= 1000 ; i+=10) {
			
			
			dr.addX(i);
			dr.addY(i);
			
			
			dr.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
