package object1;

/*
 * 1. Draw points
 * 
 */

public class DrawChickenTask implements Task {	

	double x; // x-axis (horizontally to the right)
    double y; // y-axis (vertically going down)
    
//    DrawFactory panel = new DrawFactory();    
    
    DrawChickenTask() {
		
		//panel.add(new DrawRectangle());
		
	    //panel.addX(100);
	    //panel.addY(100);
	    //panel.addX(600);
	   // panel.addY(700);
	    //panel.repaint();
	}	
	
	public void update(Chicken chicken) {
		
		System.out.println("draw point "); // sign to award DrawChickenTask successful was called 
		
		// get coordinates of one point
		x = chicken.getCoordinates().getX()*10 + 20;
		y = chicken.getCoordinates().getY()*5 + 20;		
		
//		Point2D.Double p = new Point2D.Double(x, y);
		
//		Ellipse2D E2D = new Ellipse2D.Double(x, y, 3, 3);
		
		System.out.println(x);
		System.out.println(y);
		
		System.out.println(); // break line aim to beauty, to easily to read
		
//	    panel.addX(x);
//	    panel.addY(y);
//	    panel.repaint(); 
		
	}
}
