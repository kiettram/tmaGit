package object1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 1. Draw grid
 * 2. Draw START button
 * 3. Draw 5 anchors
 */

public class Preparation implements ActionListener {
	
	static final int WIDTH_RECTANGLE = 1840;
	static final int HIGH_RECTANGLE = 1000;
	
	static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;
	
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	
	double x; // e coordinate
	double y; // y coordinate
	
	JButton startButton;
	
	int colorCount = 0;
	
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
		
		mainFrame.setSize(2000,1100);
			
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
		JPanel panel = new JPanel(); // grid
		panel.setBackground(Color.BLUE);
		panel.setSize(WIDTH_GRID_PANEL,HIGH_GRID_PANEL);
		panel.setLocation(0,0);
	  
		BorderLayout layout = new BorderLayout();
		layout.setHgap(100);
		layout.setVgap(100);
		panel.setLayout(layout);
		
        Grid grid = new Grid();
		panel.add(grid, BorderLayout.CENTER);
//		panel.validate();		
//		panel.repaint();
        
        gridPanel.add(panel); // add grid on to grid panel
        grid.repaint(); // repaint
        
        // get x, y 
		
		for (int i = 0; i < 120; i++) {
			
			// limit the amount of point shown
			if (grid.getxList().size() == 10) {
				grid.getxList().remove(0);
				grid.getyList().remove(0);
			}
			
			grid.addX(i * 10);
			grid.addY(i * 10);
			panel.repaint();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}  
        
        
        // 3. draw 5 anchors
        grid.anchor(0, 0); 		// anchor 1
        grid.anchor(183, 0); 	// anchor 2
      	grid.anchor(0, 99); 	// anchor 3
      	grid.anchor(183, 99); 	// anchor 4
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
