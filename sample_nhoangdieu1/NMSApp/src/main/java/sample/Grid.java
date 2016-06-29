package sample;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/*
 * grid component here and 5 anchors configured
 */
@SuppressWarnings("serial")
public class Grid extends JPanel{

    private List<Point> anchors; // anchors list
    
    private int w = Preparation.WIDTH_RECTANGLE;
    private int h = Preparation.HIGH_RECTANGLE;
    
    private int wOval = 200; // radius of anchors
    private int hOval = 200; // radius of anchors
    
    private List<Double> xList = new ArrayList<>(); // xlist to save x coordinates of point
    private List<Double> yList = new ArrayList<>(); // yList to save y coordinates of point
    
    // add new x coordinate into xList
    public double addX (double x) {
    	xList.add(x);
		return x;
    }
    
    // add new y coordinate into yList
    public double addY (double y) {
    	yList.add(y);
		return y;
    }
    
    public Grid() {
    	anchors = new ArrayList<>(25); // new anchors list
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        // moi lan co location update mơi -> add point vo day, goi ham repaint -> se goi ham paintComponent nay
        // -> trong day e se lay list x,y roi ve lai.
        // -> thiet ke hien tai no ko thay duoc object cua lop nay dau
        // hoac neu dung 1 jpanel khac, thi lam tuong tu voi jpanel do.
        // ve lai duông di trong ham paintConponent nay
        
        System.out.println("paint component GRID");
        
        // draw 5 anchors in "point" list
        for (Point anchor : anchors) {
            int cellX = 10 + (anchor.x * 10);
            int cellY = 10 + (anchor.y * 10);
            g2d.setColor(Color.RED);
            g2d.fillRect(cellX, cellY, 10, 10);
            g2d.drawOval(cellX-wOval/2, cellY-hOval/2, wOval, hOval);
            g2d.setColor(Color.BLUE);
            g2d.drawString("anchor " + anchor, cellX, cellY);
//            i++;
//            if (i > 5) i=0;
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
        
        
//        // draw points 
//        for (int i = 0 ; i <= xList.size() ; i ++) {
//        	
//        	double x = xList.get(i);
//        	double y = yList.get(i);
//        	
//        	g2d.setColor(Color.RED);
//        	Ellipse2D e2d = new Ellipse2D.Double(x, y, 10, 10);
//        	g2d.draw(e2d);
//        	g2d.fill(e2d);
//        	        	
//        }
        
    }

    public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }

	public List<Double> getxList() {
		return xList;
	}

	public void setxList(List<Double> xList) {
		this.xList = xList;
	}

	public List<Double> getyList() {
		return yList;
	}

	public void setyList(List<Double> yList) {
		this.yList = yList;
	}
    
    
}
