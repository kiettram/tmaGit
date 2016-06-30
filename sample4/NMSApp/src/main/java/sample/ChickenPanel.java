package sample;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ChickenPanel extends JPanel {

	private static final long serialVersionUID = -2547237036084072309L;

	private static final int DEFAULT_SIZE = 10;
	private static final int S = 10;

	// neu nhu hien tai, xs la danh sach x cua tat ca cac con ga
	// -> muon moi con co 10 diem thi phai lam sao -> thiet ke lai
	// e dua tren khung nhu the nay roi apply qua ben kia xem
	// ve duoc nhu cua a vay di
	// roi them nhung thu khac vo tu tu, da
	// co hiu may cai luong nay.
	
	private List<Point> anchors = new ArrayList<>(25); // anchors list
    
    private int w = Preparation.WIDTH_RECTANGLE;
    private int h = Preparation.HIGH_RECTANGLE;
    
    private static final int WIDTH_OVAL = 200; // radius of anchors
    private static final int HIGH_OVAL = 200; // radius of anchors	
	
	private List<Integer> xList = new ArrayList<>(); // xlist to save x coordinates of point
	private List<Integer> yList = new ArrayList<>(); // yList to save y coordinates of point

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		// ve x va y
		for (int i=0; i<xList.size();i++) {
			int x = xList.get(i);
			int y = yList.get(i);
			g2d.fillOval(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
			g2d.setColor(Color.RED);
			if (i > 0) 
			// ve duong noi - draw connection line
			g2d.drawLine(x+DEFAULT_SIZE/2, y+DEFAULT_SIZE/2, xList.get(i-1)+DEFAULT_SIZE/2, yList.get(i-1)+DEFAULT_SIZE/2);
			g2d.setColor(Color.BLACK);
		}
		
		
		System.out.println("paint component GRID");
        
        // draw 5 anchors in "point" list
        for (Point anchor : anchors) {
            int cellX = (anchor.x * 5);
            int cellY = (anchor.y * 5);
            g2d.setColor(Color.RED);
            g2d.fillOval(cellX, cellY, 10, 10);
            g2d.drawOval(cellX-WIDTH_OVAL/2, cellY-HIGH_OVAL/2, WIDTH_OVAL, HIGH_OVAL);
            g2d.setColor(Color.BLUE);
            g2d.drawString("anchor " + anchor, cellX, cellY);
        }
        
        g2d.setColor(Color.BLACK);
//        g2d.drawRect(10, 10, w, h); // rectangle cover around grid

        // draw horizontal line
        for (int i = 0; i <= w; i += 10) {
//        	g2d.setColor(Color.BLUE);
            g2d.drawLine(i, 0, i, (h+10));
            g2d.setColor(Color.BLACK);
        }

        //  draw vertical line
        for (int i = 0; i <= h; i += 10) {
//        	g2d.setColor(Color.BLUE);
            g2d.drawLine(0, i, (w+10), i);
            g2d.setColor(Color.BLACK);
        }
        
        
        
//        for (int z = 0; z <= w; z += 5) {
//        	g2d.setColor(Color.GRAY);
//            g2d.drawLine(z, 0, z, (h+10));
//            g2d.setColor(Color.BLACK);
//        }
//
//        //  draw vertical line
//        for (int t = 0; t <= h; t += 5) {
//        	g2d.setColor(Color.GREEN);
//            g2d.drawLine(0, t, (w+10), t);
//            g2d.setColor(Color.BLACK);
//        }
	}

	public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }
	
	public void draw(Chicken chicken) {

		// dung giai thuat lam tron de xac dinh chinh xac diem tren grid
		int x = (int) chicken.getCoordinates().getX() * S;
		int y = (int) chicken.getCoordinates().getY() * S/2;
		
		System.out.println("x = " + x + "\ny = " + y);

		// add x va y
		if(xList.size()==10) {
			xList.remove(0);
		}
		xList.add(x);
		
		if(yList.size()==10) {
			yList.remove(0);
		}
		yList.add(y);
		
		repaint();
	}
}
