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
		frame.setLocationRelativeTo(null);
		frame.setSize(1280, 968);
		
		SampleStorePanel ssp = new SampleStorePanel();
		frame.add(ssp);
		Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
		Set<String> keys = points.keySet();
		// add
//		Chicken chiken = null;
//		int x = (int) chiken.getCoordinates().getX();
//		int y = (int) chiken.getCoordinates().getY();
//		Coor c = update(new Coor(x, y));
		/**************************************************************************************/
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		Map<String, Color> colorMap = new HashMap<String, Color>();
		String key = "";
		Color idColor = null;
		String line = ""; // variable used to read line by line
		try {
			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));
			
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				System.out.println(chicken);
				key = chicken.getId(); // get value of key map chicken
				
				// points Map
				int x = (int) chicken.getCoordinates().getX();
				int y = (int) chicken.getCoordinates().getY();
				
				Coor c = new Coor(x, y);
				List<Coor> coor = new ArrayList<Coor>();
				coor.add(c);
				if (points.containsKey(key)) {
					points.get(key);
				}
				else {
					points.put(key, coor);
				}
				
				// color Map
				for (String key1 : keys) { // for loop 1, for each chicken
					// key -> color
					if(colorMap.containsKey(key1)) { // colorMap contains key, 
						// get color
						colorMap.get(key1);
					} else {
						// random color -> put to colors
						// new id color
						idColor = new Color(
								(int) (Math.random() * 255),
								(int) (Math.random() * 255),
								(int) (Math.random() * 255)); // create a new color for new chicken
						colorMap.put(key1, idColor);
					}
					List<Coor> coors = points.get(key); // get key - chicken id 
					for (Coor coor1 : coors) { // for loop 2, for the times its appear
						ssp.setColor(idColor);
						ssp.draw(coor1, idColor); // draw chicken id with color
					}
				}
				System.out.println("colorMap size = " + colorMap.size()); // return the number of map's elements
				System.out.println("colorMap keys = " + colorMap.keySet());
				System.out.println("colorMap values = " + colorMap.values());
				System.out.println();
				
				System.out.println("points Map size = " + points.size()); // return the number of map's elements
				System.out.println("points Map keys = " + points.keySet());
				System.out.println("points Map values = " + points.values());
				System.out.println(c.x + " - " + c.y);
				System.out.println();
				
//				System.out.println("Color value = " + idColor); // show the value with the key
//				chickenPanel.setColor(idColor);
//				chickenPanel.draw(chicken); // draw on ChickenPanel class
//				chickenPanel.repaint();
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

//	public static void paint(Coor coor, Color color) {
//		System.out.println("paint method (" + coor.x + "," + coor.y + ")");
//	}
}
