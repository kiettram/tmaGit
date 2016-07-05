package object1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import com.google.gson.Gson;

public class SampleStore {
	public static void main(String[] args) {
		JFrame frame = new JFrame("frame sample store panel");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
		frame.setSize(2000, 1100);
		
		SampleStorePanel ssp = new SampleStorePanel();
//		ssp.anchor(25, 25); 	// anchor 1
//		ssp.anchor(185, 25); 	// anchor 2
//		ssp.anchor(25, 125); 	// anchor 3
//		ssp.anchor(185, 95); 	// anchor 4
//        ssp.anchor(45, 45); 	// anchor 5
		frame.add(ssp);
		Map<String, List<Coor>> points = new HashMap<String, List<Coor>>(); // Map save a pair chicken's id and its points coordinates
		Set<String> keys = points.keySet(); // Set save points key as a list
		// add
//		Chicken chiken = null;
//		int x = (int) chiken.getCoordinates().getX();
//		int y = (int) chiken.getCoordinates().getY();
//		Coor c = update(new Coor(x, y));
		/**************************************************************************************/
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		Map<String, Color> colorMap = new HashMap<String, Color>(); // Map save a pair chicken's id and its color was create randomly
		String key = "";
		Color idColor = null;
		String line = ""; // variable used to read line by line
		try {
			File file = new File("src/main/resources/tempJson.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));
			
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				System.out.println(chicken);
				key = chicken.getId(); // get value of key map chicken
				
				// the two coordinates of a point in points Map
				int x = (int) chicken.getCoordinates().getX();
				int y = (int) chicken.getCoordinates().getY();
				/*
				 * add coordinates into array list 
				 */
				Coor c = new Coor(x, y);
				List<Coor> coors = new ArrayList<Coor>(); // [{},{},{},{}] 
				coors.add(c);
				/*
				 * check key exists on points Map or not 
				 */
				if (points.containsKey(key)) {
					points.get(key); // get coordinate
					points.put(key, coors);
				}
				else {
					points.put(key, coors); // add id chicken and its coordinates
				}
				System.out.println("points = " + points);
				System.out.println("color Map = " + colorMap);
				/**************************************************************************************/
				// color Map
				for (String key1 : keys) { // for-each loop 1, for each chicken					
					System.out.println("key1 = " + key1);
					// key -> color
					if(colorMap.containsKey(key1)) { // colorMap contains key, 
						idColor = colorMap.get(key1); // get color
					} else {
						// random color -> put to colors
						// new id color
						
						idColor = new Color(
								(int) (Math.random() * 255),
								(int) (Math.random() * 255),
								(int) (Math.random() * 255)); // create a new color for new chicken
						colorMap.put(key1, idColor);
					}
					
					List<Coor> listcoors = points.get(key1); // get key - return the coordinates of equivalent key
					System.out.println("coors list = " + points);
					for (Coor coor1 : listcoors) { // for-each loop 2, for the times its appear
						System.out.println("coors = " + listcoors);
						ssp.draw(coor1, colorMap.get(key1)); // draw chicken id with color
						ssp.repaint();
					}
//					ssp.draw(listcoors.get(0), colorMap.get(key1));
//					ssp.repaint();
				}
				/**************************************************************************************/				
								
				System.out.println("colorMap size = " + colorMap.size()); // return the number of map's elements
				System.out.println("colorMap keys = " + colorMap.keySet());
				System.out.println("colorMap values = " + colorMap.values());
				System.out.println();
				
				System.out.println("points Map size = " + points.size()); // return the number of map's elements
				System.out.println("points Map keys = " + points.keySet());
				System.out.println("points Map values = " + points.values());
				System.out.println(c.x + " - " + c.y);
				System.out.println();
				Thread.sleep(1000); // sleep in millisecond
			}

		} catch (Exception e) {
		} finally {
			try {
				fis.close(); // Close File Input Stream
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close(); // Close Buffered Reader
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		/**************************************************************************************/
	}
	
	public static Coor update(Coor c) {
		// configure for suitable with grid 
		
		
		
		
		
		return c;
	}
}
