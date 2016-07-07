package object1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import object1.observerFolder.Chicken;

public class SampleStorePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final int GRID_WIDTH = 10;
	private static final int SCALE = 10;
	private static final int ANCHOR_SCALE = 5;
//	private static final int HISTORY_POINTS = 10;
	private static final int DEFAULT_SIZE = 10;
    private static final int WIDTH_RECTANGLE = 1850;
	private static final int HIGH_RECTANGLE = 1050;
	private List<Point> anchors = new ArrayList<>(25); // anchors list
	private Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
	private Map<String, Color> colors = new HashMap<String, Color>();
	private List<Double> d = new ArrayList<Double>(); 
	private List<Dimension> dimension = new ArrayList<Dimension>();
	
	// method used to draw anchors 
	public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		Graphics2D g2d = (Graphics2D) g;
		
        // draw horizontal line
        for (int i = 0; i <= WIDTH_RECTANGLE; i += GRID_WIDTH) {
            g2d.drawLine(i, 0, i, (HIGH_RECTANGLE + GRID_WIDTH));
        }

        //  draw vertical line
        for (int i = 0; i <= HIGH_RECTANGLE; i += GRID_WIDTH) {
            g2d.drawLine(0, i, (WIDTH_RECTANGLE + GRID_WIDTH), i);
        }
        
        Set<String> keys = points.keySet();
        for (String key : keys) {
        	List<Coor> coors = points.get(key);
        	Color color = colors.get(key);
        	for(Coor coor : coors) {
        		g2d.setColor(color); // set color 
    			g2d.fillOval(coor.x, coor.y, DEFAULT_SIZE, DEFAULT_SIZE); // draw point from x and y coordinate from file
    			
    			g2d.setColor(Color.RED);
    			int i = coors.size();
    			if (i >= 2) { // the same chicken-id appear
    				for (int j = i-1 ; j > 0 ; j--) { // n points <=> n-1 lines
    					g2d.drawLine(coors.get(j).x + DEFAULT_SIZE/2, coors.get(j).y + DEFAULT_SIZE/2, 
    							 coors.get(j - 1).x + DEFAULT_SIZE/2, coors.get(j - 1).y + DEFAULT_SIZE/2); // draw connection line
    					// 10 steps rule
//    					if(i == HISTORY_POINTS + 1) { // the 11th chicken appear
//	        				coors.remove(i - 11);
//    					}    					
    				}
    			}
    			g2d.setColor(Color.BLACK);	
        	}
		}

		// draw 5 anchors in "point" list
        int cell1X = (105 * ANCHOR_SCALE);
        int cell1Y = (55 * ANCHOR_SCALE);
        int cell2X = (345 * ANCHOR_SCALE);
        int cell2Y = (25 * ANCHOR_SCALE);
        int cell3X = (25 * ANCHOR_SCALE);
        int cell3Y = (185 * ANCHOR_SCALE);
        int cell4X = (325 * ANCHOR_SCALE);
        int cell4Y = (165 * ANCHOR_SCALE);
        int cell5X = (205 * ANCHOR_SCALE);
        int cell5Y = (105 * ANCHOR_SCALE);
        Dimension dimension1 = new Dimension(cell1X, cell1Y);
        Dimension dimension2 = new Dimension(cell2X, cell2Y);
        Dimension dimension3 = new Dimension(cell3X, cell3Y);
        Dimension dimension4 = new Dimension(cell4X, cell4Y);
        Dimension dimension5 = new Dimension(cell5X, cell5Y);
        dimension.add(dimension1);
        dimension.add(dimension2);
        dimension.add(dimension3);
        dimension.add(dimension4);
        dimension.add(dimension5);
        for (Dimension dimen : dimension) {
        	int disc = (int) (d.get(0) * 5);
        	g2d.setColor(Color.RED);
        	g2d.fillOval(round5(dimen.width), round5(dimen.height), DEFAULT_SIZE, DEFAULT_SIZE);
        	g2d.drawOval(round5(dimen.width- disc/2), round5(dimen.height - disc/2), disc, disc);
//        	d.remove(i);
//        	dimension.remove(i);
        }
//        dimension.retainAll(dimension);
//        d.retainAll(d);
        g2d.setColor(Color.BLUE);
//            g2d.drawString("anchor " + anchor, cellX, cellY);
	}
	
	public void draw(Chicken chicken) {
		String key = chicken.getId(); // get value of key map chicken

		// the two coordinates of a point in points Map
		int x = (int) (chicken.getCoordinates().getX() * SCALE);
		int y = (int) (chicken.getCoordinates().getY() * SCALE);
		
		// anchors distance
		double d1 = chicken.getMeas().get(0).getDist();
		double d2 = chicken.getMeas().get(1).getDist();
		double d3 = chicken.getMeas().get(2).getDist();
		double d4 = chicken.getMeas().get(3).getDist();
		double d5 = chicken.getMeas().get(4).getDist();
		d.add(d1);
		d.add(d2);
		d.add(d3);
		d.add(d4);
		d.add(d5);
		// use round algorithm to fit with grid
		// constraints - make point in the middle of two lines in grid
		x = round5(x) + 500;
		y = round5(y) + 200;
		Coor coor = new Coor(x, y);
		if (points.containsKey(key)) {
			points.get(key).add(coor);
		}
		else {
			List<Coor> coors = new ArrayList<>();
			coors.add(coor);
			points.put(key, coors); // add id chicken and its coordinates
		}
		
		if(!colors.containsKey(key)) {
			Color idColor = new Color(
					(int) (Math.random() * 255),
					(int) (Math.random() * 255),
					(int) (Math.random() * 255)); 
			colors.put(key, idColor);
		}
		repaint();
	}
	
	public int round5 (int i) {
		// use round algorithm to fit with grid
		int XsurplusOf10 = i % 10;
		if (XsurplusOf10 != 0) {
			if (XsurplusOf10 < 5) i += (5 - XsurplusOf10);
			else i -= (XsurplusOf10 - 5);				
		}
		else i += 5; // x % 10 = 0
		return i;
	}
}
