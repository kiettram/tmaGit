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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;

/*
 * 1. Draw grid
 * 2. Draw START button
 * 3. Draw 5 anchors
 */

public class Preparation {
	
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
	
	JButton startButton;
	
	int colorCount = 0;
	
	private ChickenPanel chickenPanel = new ChickenPanel();
	
	Preparation() throws InterruptedException {
		prepareGUI();
		drawGridPanel();
		//readDataFile();
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
		
		controlPanel.add(startButton);
				
		mainFrame.add(gridPanel, BorderLayout.CENTER); // add main panel to main frame
		mainFrame.add(controlPanel, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true); // show frame
		mainFrame.setLocationRelativeTo(null); // show frame in the monitor's middle
	}
		
	// 1. Draw grid
	public void drawGridPanel () throws InterruptedException {
        
        gridPanel.add(chickenPanel, BorderLayout.CENTER);
        chickenPanel.repaint();
        
        // 3. draw 5 anchors
        chickenPanel.anchor(25, 25); 		// anchor 1
        chickenPanel.anchor(180, 20); 	// anchor 2
        chickenPanel.anchor(20, 100); 	// anchor 3
        chickenPanel.anchor(180, 100); 	// anchor 4
        chickenPanel.anchor(50, 50); 	// anchor 5
        
//        startButton.addActionListener(new StartButton(chickenPanel)); // start bu
        
//        chickenPanel.repaint();
        
        System.out.println("go hereeeeeeeeeeeeeeeeeeeeeee");
        
        //Thread.sleep(5000);
        
        //readDataFile();
        
        startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				readDataFile();
				
			}
		}); // start button listen event
        
        //readDataFile();
	}
	
	public void readDataFile2() {
		System.out.println("something");
	}

	/*
	@Override
	public void actionPerformed(ActionEvent arg0) {
		readDataFile();
	}
	*/
	
	public void readDataFile() {
		System.out.println("Start Pressed");
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;

		
		try {

			File file = new File("src/main/resources/json_file.raw");
			System.out.println("1");
			fis = new FileInputStream(file); // Open File Input Stream
			System.out.println("2");
			br = new BufferedReader(new InputStreamReader(fis));
			System.out.println("3");

			String line; // variable used to read line by line
			
			
			System.out.println("aaaaaaaaaaaa");
			
			
			//line = br.readLine();
			
		    Timer timer = new Timer();
		    timer.scheduleAtFixedRate(new RemindTask(gson, br, fis), 0, 1000);
			
		    /*
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser

				chickenPanel.draw(chicken);
				chickenPanel.repaint();
//				mainFrame.repaint();
				
				Thread.sleep(1); // sleep in millisecond
				

			}
			*/
			

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			/*
			try {
				fis.close(); // Close File Input Stream
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			/*
			try {
				br.close(); // Close Buffered Reader
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			*/
		}
	}
	
  class RemindTask extends TimerTask {
	  Gson gson;
	  BufferedReader br;
	  FileInputStream fis;
	  
	  public RemindTask(Gson gson, BufferedReader br, FileInputStream fis) {
		  this.gson = gson;
		  this.br = br;
		  this.fis = fis;
	  }
	  
	  	public void run() {
	    	System.out.println("Time's up!");
	    	String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				System.out.println("here");
				e.printStackTrace();
			}
			
			if ( line == null ) {
				try {
					fis.close(); // Close File Input Stream
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.cancel();
			} else {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser

				System.out.println(chicken);
				chickenPanel.draw(chicken);
				chickenPanel.repaint();
			} 

	    }
  }
	
	public static void main(String[] args) throws InterruptedException {
		Preparation p = new Preparation();
//		p.readDataFile();
	}
	
	// ok, a xem dum e cai cho add button listener ak, neu em su dung nhan nut add thi no ko chay, ko dung thi no chay
	
	// ve mau tung con, duong noi chi noi cua 1 con lai voi nhau
	
	// vong tron xung quanh 1 diem la gi?
	// luc truoc e thay trong demo co vong tron, 
	// ko phai xung quanh 1 diem dau, bo ra di -> xung quanh may anchors
	// da, vay la e hiu sai y 
	// ko biet sao nua, e lam tiep may cai kia di, de a coi lai ldoia, da
	// chao anh
}
