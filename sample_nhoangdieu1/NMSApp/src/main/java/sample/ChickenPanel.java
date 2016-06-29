package sample;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import object1.Chicken;

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
			g2d.fillOval(xList.get(i), yList.get(i), DEFAULT_SIZE, DEFAULT_SIZE);
		}
		
		
		System.out.println("paint component GRID");
        
        // draw 5 anchors in "point" list
        for (Point anchor : anchors) {
            int cellX = 10 + (anchor.x * 10);
            int cellY = 10 + (anchor.y * 10);
            g2d.setColor(Color.RED);
            g2d.fillOval(cellX, cellY, 10, 10);
            g2d.drawOval(cellX-WIDTH_OVAL/2, cellY-HIGH_OVAL/2, WIDTH_OVAL, HIGH_OVAL);
            g2d.setColor(Color.BLUE);
            g2d.drawString("anchor " + anchor, cellX, cellY);
        }
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(10, 10, w, h); // rectangle cover around grid

        // draw horizontal line
        for (int i = 10; i <= w; i += 10) {
            g.drawLine(i, 10, i, (h+10));
        }

        //  draw vertical line
        for (int i = 10; i <= h; i += 10) {
            g.drawLine(10, i, (w+10), i);
        }
	}

	public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }
	
	public void draw(Chicken chicken) {

		// dung giai thuat lam tron de xac dinh chinh xac diem tren grid
		int x = (int) chicken.getCoordinates().getX() * S;
		int y = (int) chicken.getCoordinates().getY() * S;

		// add x va y
		if(xList.size()==10) {
			xList.remove(0);
		}
		xList.add(x);
		
		if(yList.size()==10) {
			yList.remove(0);
		}
		yList.add(y);
	}
}
