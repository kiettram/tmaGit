package object1;

/*
 * 1. Draw points
 * 2. Draw color of specific point
 */

public class DrawChickenTask implements Task {

	double x; // x-axis (horizontally to the right)
	double y; // y-axis (vertically going down)

	public DrawChickenTask() {

	}

	public void update(Chicken chicken) {

		System.out.println("DrawChickenTask UPDATE method was called "); 

		// get coordinates of one point
		x = chicken.getCoordinates().getX();
		y = chicken.getCoordinates().getY();

		// show x and y
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
	}
}
