package object1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;

import object1.Chicken;

/*
 * 1. Draw grid
 * 2. Draw START button
 * 3. Draw 5 anchors
 */

public class Preparation implements ActionListener {
	
	static final int WIDTH_RECTANGLE = 1850;
	static final int HIGH_RECTANGLE = 1010;
	
	static final int FRAME_WIDTH_SIZE = 2000;
	static final int FRAME_HIGH_SIZE = 1100;
	
	static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;
	
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	
	double x; // e coordinate
	double y; // y coordinate
	
	private Map<String, Color> idMap;
	private Color idColor;
	private String key = "";
	
	JButton startButton;
	
	int colorCount = 0;
	
	private ChickenPanel chickenPanel = new ChickenPanel();
	
	Preparation() {
		prepareGUI();
		drawGridPanel();
	}

	// draw frame, gridPanel, controlPanel, button
	private void prepareGUI() {
		// draw frame 
		mainFrame = new JFrame();
		mainFrame.setTitle("Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		
		mainFrame.setSize(FRAME_WIDTH_SIZE, FRAME_HIGH_SIZE);
			
		// draw grid panel
		gridPanel = new JPanel();
		gridPanel.setLayout(new BorderLayout());
		gridPanel.setBackground(Color.PINK);
		
		// draw control panel
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		controlPanel.setBackground(Color.DARK_GRAY);
		
		// 2. draw START button
		startButton = new JButton();
		startButton.setText("START");		
		startButton.addActionListener(this); // start button listen event
		controlPanel.add(startButton);
				
		mainFrame.add(gridPanel, BorderLayout.CENTER); // add main panel to main frame
		mainFrame.add(controlPanel, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true); // show frame
		mainFrame.setLocationRelativeTo(null); // show frame in the monitor's middle
	}
		
	// 1. Draw grid
	public void drawGridPanel () {
        
        gridPanel.add(chickenPanel, BorderLayout.CENTER);
        chickenPanel.repaint();
        
        
        
        // 3. draw 5 anchors
        chickenPanel.anchor(0, 0); 		// anchor 1
        chickenPanel.anchor(183, 0); 	// anchor 2
        chickenPanel.anchor(0, 99); 	// anchor 3
        chickenPanel.anchor(183, 99); 	// anchor 4
        chickenPanel.anchor(40, 40); 	// anchor 5
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {}
	
	public void readDataFile() {
				
		/**************************************************************************************/
		ChickenObserver obj = new ChickenObserver(); // lass ChickenObserver - observer pattern
		// Tasks list here
		Task draw = new DrawChickenTask(); // create new task
		obj.registerTask(draw); // all observers register new task
		// nhung cai task nay nen duoc register tu luc khoi tao object
		/**************************************************************************************/
		
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;

		idMap = new HashMap<String, Color>();
		try {

			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));

			String line = ""; // variable used to read line by line
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				
				obj.notifyUpdate(chicken); // update all task
				
				System.out.println(chicken);
				key = chicken.getId(); // get value of key map chicken
				// check id chicken in map is exists or not
				if(!idMap.containsKey(key)){ // id chicken not exists 
					// new id color
					idColor = new Color(
							(int) (Math.random() * 255),
							(int) (Math.random() * 255),
							(int) (Math.random() * 255)); // create a new color for new chicken
					
					// save chicken's id and its color into ID Map 
					// random co khi cung trung lai, nen kiem tra truoc la ko trung thi moi add voo
					idMap.put(key, idColor); 
				}
				else { // id chicken exists
					idColor = (Color) idMap.get(key);
				}
				
				// show id color
				System.out.println("Chicken id Map = " + idMap.size()); // return the number of map's elements
				System.out.println("Color value = " + idColor); // show the value with the key
				
				chickenPanel.setColor(idColor);
				chickenPanel.draw(chicken); // draw on ChickenPanel class
				chickenPanel.repaint();
				
				Thread.sleep(1000); // sleep in millisecond

			}

		} catch (Exception e) {
			// ignore
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
	
	// draw color 
	public void drawColorChicken () {
		
	}
	
}
