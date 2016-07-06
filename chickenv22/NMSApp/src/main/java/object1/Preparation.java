package object1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.google.gson.Gson;
import object1.observerFolder.Chicken;
import object1.observerFolder.ChickenObserver;
import object1.observerFolder.Task;
/*
 * 1. Draw grid
 * 2. Draw START button
 * 3. Draw 5 anchors
 */
public class Preparation{
	private static final int FRAME_WIDTH_SIZE = 2000;
	private static final int FRAME_HIGH_SIZE = 1100;
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	private JButton startButton;
	private SampleStorePanel ssp = new SampleStorePanel();
	
	public Preparation() {
		prepareGUI();
		drawGridPanel();
		readDataFile();
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
		controlPanel.setVisible(false);
		
		// 2. draw START button
		startButton = new JButton();
		startButton.setText("START");		
//		startButton.addActionListener(this); // start button listen event
		controlPanel.add(startButton);
				
		mainFrame.add(gridPanel, BorderLayout.CENTER); // add main panel to main frame
		mainFrame.add(controlPanel, BorderLayout.SOUTH);
		mainFrame.setLocationRelativeTo(null); // show frame in the monitor's middle
		mainFrame.setVisible(true); // show frame
	}
		
	// 1. Draw grid
	public void drawGridPanel () {
        gridPanel.add(ssp, BorderLayout.CENTER);
        ssp.repaint();
        // 3. draw 5 anchors
		ssp.anchor(105, 55); 	// anchor 1, quadrant
		ssp.anchor(345, 25); 	// anchor 2
		ssp.anchor(25, 185); 	// anchor 3
		ssp.anchor(325, 165); 	// anchor 4
        ssp.anchor(205, 105); 	// anchor 5
	}
	
	public void readDataFile() {
		// observer pattern
		ChickenObserver obj = new ChickenObserver(); // lass ChickenObserver - observer pattern
		// Tasks list here
		Task draw = new DrawChickenTask(); // create new task
		obj.registerTask(draw); // all observers register new task
		
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));

			String line = ""; // variable used to read line by line
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				
				obj.notifyUpdate(chicken); // update all task
				ssp.draw(chicken);
				Thread.sleep(10); // sleep in millisecond
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
	}
}
