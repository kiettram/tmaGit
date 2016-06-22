package object1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class ReadFile2 {
	
	Map idMap;
	Color idColor;
	private int i = 0;
	private int j = 0;
	private int k = 0;
	
	public ReadFile2() {
		
		readFile();
		
	}

	public void readFile () { 		
		ChickenObserver obj = new ChickenObserver(); 
				
		Task draw = new DrawChickenTask(); // create new task
		obj.registerTask(draw); // all observers register new task 
		
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		
		// map save chicken's id and its color
		idMap = new HashMap();
		
		// new color
//		idColor = new Color(r, g, b)
		
		try {
			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis)); // Open Buffered Reader
			
			String line; // variable used to read line by line 			
			while ((line = br.readLine()) != null) {	
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				obj.notifyUpdate(chicken);
				System.out.println(chicken); // show chicken in a line
				
				// check id chicken in map is exists or not
				if(idMap.containsKey(chicken.getId()) == false){ // id chicken not exists 
					// new id color
//					for (int i = 0; i <= 255 ; i+=5)
//						for (int j = 0; j <= 25 ; j+=5)
//							for (int k = 0 ; k <= 255 ; k+=5)
								idColor = new Color(i,j,k); // create a new color for new chicken
								// save chicken's id into IDMap
								idMap.put(chicken.getId(), idColor); 
				} // end if
				
				// show id color
				System.out.println("Chicken id Map = " + idMap.size()); // return the number of map's elements
				System.out.println("Color value = " + idMap.get(chicken.getId())); // show the value with the key
				System.out.println();
				
				// Check a key is exists in map or not
//				String searchKey = "chicken-95";
//				if(idMap.containsKey(searchKey))
//					System.out.println("Found total " + idMap.get(searchKey) + " " + searchKey + "!\n");
//				else 
//					System.out.println(searchKey + "not found!");
				
				Thread.sleep(2000); // sleep 1 second 			
				i += 1;
				if (i == 255) {
//					i = 0;
					j += 1;
				}
				if (j == 255) {
//					j = 0;
					k += 1;
				}
				if (k == 255) {
					i = 0;
					j = 0;
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	}	
}
