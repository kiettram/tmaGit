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
	private String key;
	
	public ReadFile2() {		
		readFile();	// call read file method
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
				
		try {
			File file = new File("src/main/resources/json_file.raw"); // read destination file
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis)); // Open Buffered Reader
			
			String line; // variable used to read line by line 			
			while ((line = br.readLine()) != null) {	
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				obj.notifyUpdate(chicken); // update all task
				System.out.println(chicken); // show chicken in a line
				
				key = chicken.getId(); // get value of key map chicken
				
				// check id chicken in map is exists or not
				if(idMap.containsKey(key) == false){ // id chicken not exists 
					// new id color
//					for (int i = 0; i <= 255 ; i+=5)
//						for (int j = 0; j <= 25 ; j+=5)
//							for (int k = 0 ; k <= 255 ; k+=5)
								idColor = new Color(
										(int) (Math.random() * 255),
										(int) (Math.random() * 255),
										(int) (Math.random() * 255)); // create a new color for new chicken
								// save chicken's id and its color into ID Map 
								idMap.put(key, idColor); 
				} // end if
				else if(idMap.containsKey(key) == true){ // id chicken exists
					idColor = (Color) idMap.get(key);
				}
				
				// show id color
				System.out.println("Chicken id Map = " + idMap.size()); // return the number of map's elements
				System.out.println("Color value = " + idColor); // show the value with the key
				System.out.println();
				
				// Check a key is exists in map or not
//				String searchKey = "chicken-147";
//				if(idMap.containsKey(searchKey))
//					System.out.println("Found total " + idMap.get(searchKey) + " " + searchKey + "!\n");
//				else 
//					System.out.println(searchKey + "not found!");
				
				Thread.sleep(10); // sleep in millisecond 			
				
				// color was organized
				i += 1; // (0,0,0) -> (255,0,0)
				if (i == 255) { // (0,0,0) -> (255,0,0) // map elements maximum = 200 
					i = 255;
					j += 1;
				}
				if (j == 255) { // (255,0,0) -> (255,255,0)
					j = 255;
					k += 1;
				}
				if (k == 255) { // (255,255,0) -> (255,255,255)
					i = 255;
					j = 255;
					k = 255;
				}
//				if (idMap.size() == 200) break;
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
