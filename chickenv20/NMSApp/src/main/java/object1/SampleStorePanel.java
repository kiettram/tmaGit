package object1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

public class SampleStorePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private static final int SCALE = 10;
	private static final int HISTORY_POINTS = 10;
	private static final int DEFAULT_SIZE = 10;
	private static final int WIDTH_OVAL = 200; // radius of anchors
    private static final int HIGH_OVAL = 200; // radius of anchors	
    private static final int GRID_NO = 10;
    
    private int w = Preparation.WIDTH_RECTANGLE;
    private int h = Preparation.HIGH_RECTANGLE;
//	private List<Integer> xList = new ArrayList<>(); // xlist to save x coordinates of point
//	private List<Integer> yList = new ArrayList<>(); // yList to save y coordinates of point
	private List<Point> anchors = new ArrayList<>(25); // anchors list
	
//	private List<Coor> points = new ArrayList<>();
	
	private Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
	private Map<String, Color> colors = new HashMap<String, Color>();
	
	public void anchor(int x, int y) {
    	anchors.add(new Point(x, y));
        repaint();
    }
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		Graphics2D g2d = (Graphics2D) g;		
        // draw horizontal line
        for (int i = 0; i <= w; i += GRID_NO) {
            g2d.drawLine(i, 0, i, (h+10));
        }

        //  draw vertical line
        for (int i = 0; i <= h; i += GRID_NO) {
            g2d.drawLine(0, i, (w+10), i);
        }
        
        Set<String> keys = points.keySet();
        for (String key : keys) {
        	List<Coor> coors = points.get(key);
        	
        	Color color = colors.get(key);
        	
        	for(Coor coor : coors) {
        		g2d.setColor(color); // set color 
    			g2d.fillOval(coor.x, coor.y, DEFAULT_SIZE, DEFAULT_SIZE); // draw point from x and y coordinate from file
    			
//    			g2d.setColor(Color.RED);
//    				if (i > 0) // condition to connection line appear
//    				g2d.drawLine(x+DEFAULT_SIZE/2, y+DEFAULT_SIZE/2, xList.get(i-1)+DEFAULT_SIZE/2, yList.get(i-1)+DEFAULT_SIZE/2); // draw connection line
//    			g2d.setColor(Color.BLACK);	
    			
    			if(coors.size() == HISTORY_POINTS) {
    				coors.remove(0);
    			}
        	}
		}
        
		// draw x and y
//		for (int i = 0; i < xList.size() ; i++) {
//			int x = xList.get(i);
//			int y = yList.get(i);			
//			
//			g2d.setColor(color); // set color 
//			g2d.fillOval(x, y, DEFAULT_SIZE, DEFAULT_SIZE); // draw point from x and y coordinate from file
//			
//			g2d.setColor(Color.RED);
//				if (i > 0) // condition to connection line appear
//				g2d.drawLine(x+DEFAULT_SIZE/2, y+DEFAULT_SIZE/2, xList.get(i-1)+DEFAULT_SIZE/2, yList.get(i-1)+DEFAULT_SIZE/2); // draw connection line
//			g2d.setColor(Color.BLACK);			
//		}
		
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
	}
	
	private void draw(Coor coor, Color color) {
		
//		this.color = color; // preference to color variable to send its value
		
		// use round algorithm to fit with grid
		int x = coor.x * SCALE;
		int y = coor.y * SCALE;

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
		
//		System.out.println("x = " + x + "\ny = " + y);
		/**************************************************************************************/

		// add x and y
//		if(xList.size() == HISTORY_POINTS) {
//			xList.remove(0);
//		}
//		xList.add(x);
//		
//		if(yList.size() == HISTORY_POINTS) {
//			yList.remove(0);
//		}
//		yList.add(y);
		
		repaint();
	}
	
	public void draw(Chicken chicken) {
//		System.out.println(chicken);
		String key = chicken.getId(); // get value of key map chicken
		
		// the two coordinates of a point in points Map
		int x = (int) chicken.getCoordinates().getX();
		int y = (int) chicken.getCoordinates().getY();
		/*
		 * add coordinates into array list 
		 */
//		Coor c = new Coor(x, y);
//		List<Coor> coors = new ArrayList<Coor>(); // [{},{},{},{}] 
//		coors.add(c);
		/*
		 * check key exists on points Map or not 
		 */
		
		Coor coor = new Coor(x, y);
		if (points.containsKey(key)) {
//			points.get(key); // get coordinate
//			points.put(key, coors);
			
			points.get(key).add(coor);
			
		}
		else {
			List<Coor> coors = new ArrayList<>();
			coors.add(coor);
			points.put(key, coors); // add id chicken and its coordinates
		}
//		System.out.println("points = " + points);
//		System.out.println("color Map = " + colorMap);
		/**************************************************************************************/
		// color Map
//		for (String key1 : keys) { // for-each loop 1, for each chicken					
//			System.out.println("key1 = " + key1);
//			// key -> color
//			if(colorMap.containsKey(key1)) { // colorMap contains key, 
//				idColor = colorMap.get(key1); // get color
//			} else {
//				// random color -> put to colors
//				// new id color
//				
//				idColor = new Color(
//						(int) (Math.random() * 255),
//						(int) (Math.random() * 255),
//						(int) (Math.random() * 255)); // create a new color for new chicken
//				colorMap.put(key1, idColor);
//			}
//			
//			List<Coor> listcoors = points.get(key1); // get key - return the coordinates of equivalent key
//			System.out.println("coors list = " + points);
//			for (Coor coor1 : listcoors) { // for-each loop 2, for the times its appear
//				System.out.println("coors = " + listcoors);
//				ssp.draw(coor1, colorMap.get(key1)); // draw chicken id with color
//				ssp.repaint();
//			}
////			ssp.draw(listcoors.get(0), colorMap.get(key1));
////			ssp.repaint();
//		}
		
		if(!colors.containsKey(key)) {
			Color idColor = new Color(
					(int) (Math.random() * 255),
					(int) (Math.random() * 255),
					(int) (Math.random() * 255)); 
			colors.put(key, idColor);
		}
		
		repaint();
	}
}
