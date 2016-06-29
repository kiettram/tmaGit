package sample;

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
	
	static final int WIDTH_RECTANGLE = 1840;
	static final int HIGH_RECTANGLE = 1000;
	
	static final int FRAME_WIDTH_SIZE = 2000;
	static final int FRAME_HIGH_SIZE = 1100;
	
	static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;
	
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	
	double x; // e coordinate
	double y; // y coordinate
	
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
		
        Grid grid = new Grid();        
        grid.setLayout(new BorderLayout());
//        gridPanel.add(grid, BorderLayout.CENTER); // add grid on to grid panel
//        grid.repaint(); // repaint
        
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
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;

		try {

			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));

			String line; // variable used to read line by line
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser

				chickenPanel.draw(chicken);
				chickenPanel.repaint();
				
				Thread.sleep(500); // sleep in millisecond

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
	
	public static void main(String[] args) {
		Preparation p = new Preparation();
		p.readDataFile();
	}
	
}
