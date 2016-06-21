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

public class Demo implements ActionListener {
	
	static final int WIDTH_RECTANGLE = 1000;
	static final int HIGH_RECTANGLE = 900;
	
	static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;
	
	private JFrame mainFrame; // main frame
	private JPanel gridPanel; // panel used to contain grid was drawn from points
	private JPanel controlPanel; // panel contains start button
	
	JButton startButton;
	
	int colorCount = 0;
	
	Demo() {
		prepareGUI();
	}

	// draw frame, gridPanel, controlPanel, button
	private void prepareGUI() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Demo");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(840,560);
				
		gridPanel = new JPanel();
		gridPanel.setLayout(null);
		gridPanel.setBackground(Color.PINK);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		controlPanel.setBackground(Color.DARK_GRAY);
		
		startButton = new JButton();
		startButton.setText("START");		
		startButton.addActionListener(this);
		controlPanel.add(startButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		
		mainFrame.add(mainPanel);
		
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null); 
	}
	
	public static class Grid extends JPanel {

	        private List<Point> fillCells;

	        public Grid() {
	            fillCells = new ArrayList<>(25);
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            
	            for (Point fillCell : fillCells) {
	                int cellX = 10 + (fillCell.x * 10);
	                int cellY = 10 + (fillCell.y * 10);
	                g.setColor(Color.RED);
	                g.fillRect(cellX, cellY, 10, 10);
	            }
	            
	            g.setColor(Color.BLACK);
	            g.drawRect(10, 10, WIDTH_RECTANGLE, HIGH_RECTANGLE);

	            // draw horizontal line
	            for (int i = 10; i <= WIDTH_RECTANGLE; i += 10) {
	                g.drawLine(i, 10, i, (HIGH_RECTANGLE+10));
	            }

	            //  draw vertical line
	            for (int i = 10; i <= HIGH_RECTANGLE; i += 10) {
	                g.drawLine(10, i, (WIDTH_RECTANGLE+10), i);
	            }
	        }

	        public void fillCell(int x, int y) {
	            fillCells.add(new Point(x, y));
	            repaint();
	        }
	 }
	
	private void drawGridPanel () {
		JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
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
        
        gridPanel.add(panel);
        grid.fillCell(0, 0);
        grid.fillCell(99, 0);
      	grid.fillCell(0, 89);
      	grid.fillCell(99, 89);
      	grid.fillCell(50, 40);
	
	}
	
	public static void main(String[] args) {
	
		Demo demo = new Demo();
		demo.drawGridPanel();
		
	}
	
	public void readFile () { 		
		ChickenObserver obj = new ChickenObserver(); 
				
//		Task draw = new DrawChickenTask(); // create new task
//		obj.registerTask(draw); // all observers register new task 
		
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
				obj.notifyUpdate(chicken);
				System.out.println(chicken);
				//sleep
				Thread.sleep(10);
				
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		colorCount+=10;
//		if(colorCount > 255) colorCount = 0;
//		Color color = new Color(colorCount,255,colorCount);
//		
//		gridPanel.setBackground(color);
		
		// read file
		readFile();
		
		// draw point
		
		
		
		
		
	}
	
}
