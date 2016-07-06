package object1;

//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import object1.observerFolder.Chicken;
import object1.observerFolder.Task;

/*
 * 1. Draw points
 * 2. Draw color of specific point
 */

public class DrawChickenTask implements Task {

//	private static final int SCALE = 10;
//	private Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
//	private Map<String, Color> colors = new HashMap<String, Color>();
	
	public DrawChickenTask() {

	}

	public void update(Chicken chicken) {
//		System.out.println("update method of DrawChickenTask was called");
		
//		String key = chicken.getId(); // get value of key map chicken
//
//		// the two coordinates of a point in points Map
//		int x = (int) (chicken.getCoordinates().getX() * SCALE);
//		int y = (int) (chicken.getCoordinates().getY() * SCALE);
//		
//		// use round algorithm to fit with grid
//		// constraints - make point in the middle of two lines in grid
//		int XsurplusOf10 = x % 10;
//		if (XsurplusOf10 != 0) {
//			if (XsurplusOf10 < 5) x += (5 - XsurplusOf10);
//			else x -= (XsurplusOf10 - 5);				
//		}
//		else x += 5; // x % 10 = 0
//		
//		int YsurplusOf10 = y % 10;
//		if (YsurplusOf10 != 0) {
//			if (YsurplusOf10 < 5) y += (5 - YsurplusOf10);
//			else y -= (YsurplusOf10 - 5);				
//		}
//		else y += 5; // y % 10 = 0
//		
//		Coor coor = new Coor(x, y);
//		if (points.containsKey(key)) {
//			points.get(key).add(coor);
//		}
//		else {
//			List<Coor> coors = new ArrayList<>();
//			coors.add(coor);
//			points.put(key, coors); // add id chicken and its coordinates
//		}
//		
//		if(!colors.containsKey(key)) {
//			Color idColor = new Color(
//					(int) (Math.random() * 255),
//					(int) (Math.random() * 255),
//					(int) (Math.random() * 255)); 
//			colors.put(key, idColor);
//		}
//		repaint();
	}
}
