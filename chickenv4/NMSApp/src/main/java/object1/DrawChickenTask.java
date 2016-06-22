package object1;

/*
 * 1. Draw points
 * 2. Draw color of specific point
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
	    //panel.addY(700);
	    //panel.repaint();
	}	
	
	public void update(Chicken chicken) {
		
		System.out.println("Draw chicken task update method was called "); // sign to award DrawChickenTask successful was called 
		
		// get coordinates of one point
		x = chicken.getCoordinates().getX()*10 + 20;
		y = chicken.getCoordinates().getY()*5 + 20;		
		
		// show x and y
		System.out.println("x = " + x);
		System.out.println("y = " + y);		
		
//	    panel.addX(x);
//	    panel.addY(y);
//	    panel.repaint(); 
		
	}
}
