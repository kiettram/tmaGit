package object1;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawChickenTask implements Task {	

	double x; // x-axis (horizontally to the right)
    double y; // y-axis (vertically going down)

    JFrame frame = new JFrame("Draw points and shapes"); // frame used to draw on
//    JPanel monitor, control;
    // monitor: a panel used to draw points on it
    // control: a panel contains some buttons to control the monitor    
    
//    DrawFactory panel = new DrawFactory();
    
    DrawChickenTask() {
		
		// draw rectangle
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1000, 1000);
		
//		frame.add(panel);
		
		
		frame.setLocationRelativeTo(null);
//		frame.pack();
		frame.setVisible(true);
		frame.setLayout(null);
		
		//panel.add(new DrawRectangle());
		
	    //panel.addX(100);
	    //panel.addY(100);
	    //panel.addX(600);
	   // panel.addY(700);
	    //panel.repaint();
		
		//DrawFromFile points = new DrawFromFile(100,500);
		
//		frame.getContentPane().add(new DrawFromFile(x, y));
		
		//frame.add(points);
		
		
		//DrawFromFile points2 = new DrawFromFile(600,600);
		
//		frame.getContentPane().add(new DrawFromFile(x, y));
		
		//frame.add(points2);
		
		
		// add button
		JButton button = new JButton("Start");
		button.setBounds(470, 920, 100, 40);
		frame.add(button);
		

		
	}
	
	
	public void update(Chicken chicken) {
		
		System.out.println("draw point "); // sign to award DrawChickenTask successful was called 
		
		x = chicken.getCoordinates().getX()*10 + 20;
		y = chicken.getCoordinates().getY()*5 + 20;
		
		
//		Point2D.Double p = new Point2D.Double(x, y);
		
//		Ellipse2D E2D = new Ellipse2D.Double(x, y, 3, 3);
		
		System.out.println(x);
		System.out.println(y);
		
		System.out.println(); // break line aim to beauty, to easily to read
		
//	    panel.addX(x);
//	    panel.addY(y);
//	    panel.repaint(); 

		
//		DrawPoint points = new DrawPoint(x,y);
		
//		DrawPoint drawPoint = new DrawPoint(x, y);
		
		//DrawFromFile dff = new DrawFromFile(x, y);
		
		//DrawFromFile points = new DrawFromFile(100,500);
		
//		frame.getContentPane().add(new DrawFromFile(x, y));
		
		//frame.getContentPane().add(points);
		
		//frame.add(points); // draw points from file here
		
//		frame.getContentPane().add(points);
		
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
