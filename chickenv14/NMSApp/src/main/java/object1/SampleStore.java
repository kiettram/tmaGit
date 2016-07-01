package object1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SampleStore {

	public static void main(String[] args) {
		Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
		Set<String> keys = points.keySet();

		// add
		Chicken chiken = null;
		int x = (int) chiken.getCoordinates().getX();
		int y = (int) chiken.getCoordinates().getY();
		Coor c = update(new Coor(x, y));
		
		 Map<String, String> colors = new HashMap<>();
		
		for (String key : keys) {
			// key -> color
			String color= null;
			if(colors.containsKey(key)) {
				// get color
			} else {
				// random color -> put to colors
			}
			
			List<Coor> coors = points.get(key);
			for (Coor coor : coors) {
				paint(coor, color);
			}
		}
	}
	
	public static Coor update(Coor c) {
		// 
		return c;
	}

	public static void paint(Coor coor, String color) {
		System.out.println(coor.x + "-" + coor.y);
	}
}
