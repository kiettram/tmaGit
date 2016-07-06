package object1;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import object1.observerFolder.Chicken;

/*
 * draw points by drawComponent method
 */

public class ChickenPanel extends JPanel {

	private static final long serialVersionUID = -2547237036084072309L;

	private static final int DEFAULT_SIZE = 10;
	private static final int SCALE = 10;
	private static final int HISTORY_POINTS = 10;
	private static final int GRID_NO = 10;
	public static final int WIDTH_RECTANGLE = 1850;
	public static final int HIGH_RECTANGLE = 1050;
	private static final int WIDTH_GRID_PANEL = WIDTH_RECTANGLE + 20;
	private static final int HIGH_GRID_PANEL = HIGH_RECTANGLE + 20;

	// neu nhu hien tai, xs la danh sach x cua tat ca cac con ga
	// -> muon moi con co 10 diem thi phai lam sao -> thiet ke lai
	
	// roi them nhung thu khac vo tu tu
	// co hiu may cai luong nay.
	
	private List<Point> anchors = new ArrayList<>(25); // anchors list
    
    private int w = WIDTH_RECTANGLE;
    private int h = HIGH_RECTANGLE;
    
    private static final int WIDTH_OVAL = 200; // radius of anchors
    private static final int HIGH_OVAL = 200; // radius of anchors	
	
	private List<Integer> xList = new ArrayList<>(); // xlist to save x coordinates of point
	private List<Integer> yList = new ArrayList<>(); // yList to save y coordinates of point
	
	private Color idColor;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("paint component method of ChickenPanel class was called\n");
		Graphics2D g2d = (Graphics2D) g;
		
		// draw x and y
		for (int i = 0; i < xList.size() ; i++) {
			int x = xList.get(i);
			int y = yList.get(i);
			/*
			 * draw a chicken before with its color in hash map
			 * check, if key 
			 */
			// set color 
			g2d.setColor(idColor);
			
			g2d.fillOval(x, y, DEFAULT_SIZE, DEFAULT_SIZE); // draw point from x and y coordinate from file
			g2d.setColor(Color.RED);
				if (i > 0) // condition to connection line appear
				g2d.drawLine(x+DEFAULT_SIZE/2, y+DEFAULT_SIZE/2, xList.get(i-1)+DEFAULT_SIZE/2, yList.get(i-1)+DEFAULT_SIZE/2); // draw connection line
			g2d.setColor(Color.BLACK);
		}
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
        for (int i = 0; i <= w; i += GRID_NO) {
            g2d.drawLine(i, 0, i, (h+10));
        }

        //  draw vertical line
        for (int i = 0; i <= h; i += GRID_NO) {
            g2d.drawLine(0, i, (w+10), i);
        }
	}

	public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }
	
	public void setColor(Color idColor) {
		this.idColor = idColor;
	}
	
	public void draw(Chicken chicken) {

		// dung giai thuat lam tron de xac dinh chinh xac diem tren grid 
		int x = (int) (chicken.getCoordinates().getX() * SCALE);
		int y = (int) (chicken.getCoordinates().getY() * SCALE);

		/**************************************************************************************/
		// constraints - make point in the middle of two lines in grid
		int XsurplusOf10 = x % 10;
		if (XsurplusOf10 != 0) {
			if (XsurplusOf10 < 5) x += (5 - XsurplusOf10);
			else x -= (XsurplusOf10 - 5);				
		}
		else x += 5; // x % 10 = 0
		
		int YsurplusOf10 = y % 10;
		if (YsurplusOf10 != 0) {
			if (YsurplusOf10 < 5) y += (5 - YsurplusOf10);
			else y -= (YsurplusOf10 - 5);				
		}
		else y += 5; // y % 10 = 0
		
		System.out.println("x = " + x + "\ny = " + y);
		/**************************************************************************************/

		// add x va y
		if(xList.size() == HISTORY_POINTS) {
			xList.remove(0);
		}
		xList.add(x);
		
		if(yList.size() == HISTORY_POINTS) {
			yList.remove(0);
		}
		yList.add(y);
		
		repaint();
	}
}
