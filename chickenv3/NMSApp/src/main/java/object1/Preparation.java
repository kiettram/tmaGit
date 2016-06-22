package object1;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;

/*
 * 1. Draw grid
 * 2. Draw START button
 * 3. Draw 5 anchors
 */

public class Preparation implements ActionListener {
	
	static final int WIDTH_RECTANGLE = 1000;
	static final int HIGH_RECTANGLE = 900;
	
	static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;
	
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	
	JButton startButton;
	
	int colorCount = 0;
	
	Preparation() {
		prepareGUI();
	}

	// draw frame, gridPanel, controlPanel, button
	private void prepareGUI() {
		// draw frame 
		mainFrame = new JFrame();
		mainFrame.setTitle("Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(840,560);
			
		// draw grid panel
		gridPanel = new JPanel();
		gridPanel.setLayout(null);
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
		
		JPanel mainPanel = new JPanel(); // main panel contain grid panel and control panel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		
		mainFrame.add(mainPanel); // add main panel to main frame
		
		mainFrame.setVisible(true); // show frame
		mainFrame.setLocationRelativeTo(null); // show frame in the monitor's middle
	}
		
	// 1. Draw grid
	public void drawGridPanel () {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setSize(WIDTH_GRID_PANEL,HIGH_GRID_PANEL);
		panel.setLocation(400, 10);
	  
		BorderLayout layout = new BorderLayout();
		layout.setHgap(100);
		layout.setVgap(100);
		panel.setLayout(layout);      
		
        Grid grid = new Grid();		
		panel.add(grid, BorderLayout.CENTER);
		panel.validate();		
		panel.repaint();
        
        gridPanel.add(panel); // add grid on to grid panel
        // 3. draw 5 anchors
        grid.anchor(0, 0); 		// anchor 1
        grid.anchor(99, 0); 	// anchor 2
      	grid.anchor(0, 89); 	// anchor 3
      	grid.anchor(99, 89); 	// anchor 4
      	grid.anchor(40, 40); 	// anchor 5
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		colorCount+=10;
//		if(colorCount > 255) colorCount = 0;
//		Color color = new Color(colorCount,255,colorCount);		
//		gridPanel.setBackground(color);
		
		// read file
		new ReadFile2();
		
		// draw point
		
		
		
		
		
	}
	
}
