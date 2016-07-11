package finalpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;

public class DrawFunction extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int GRID_WIDTH = 10;
	private static final int SCALE = 10;
	private static final int ANCHOR_SCALE = 5;
	private static final int DEFAULT_SIZE = 10;
	private static final int HISTORY_POINTS = 10;
	private static final int WIDTH_RECTANGLE = 1850;
	private static final int HIGH_RECTANGLE = 1050;
	private Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
	private Map<String, Color> colors = new HashMap<String, Color>();
	private List<Meas> meas = new ArrayList<Meas>();
	private Set<String> keys = points.keySet();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// draw horizontal line
		for (int i = 0; i <= WIDTH_RECTANGLE; i += GRID_WIDTH) {
			g2d.drawLine(i, 0, i, (HIGH_RECTANGLE + GRID_WIDTH));
		}
		// draw vertical line
		for (int i = 0; i <= HIGH_RECTANGLE; i += GRID_WIDTH) {
			g2d.drawLine(0, i, (WIDTH_RECTANGLE + GRID_WIDTH), i);
		}
		for (String key : keys) {
			List<Coor> coors = points.get(key);
			Color color = colors.get(key);
			for (Coor coor : coors) {
				g2d.setColor(color); // set color
				g2d.fillOval(coor.x, coor.y, DEFAULT_SIZE, DEFAULT_SIZE);
				g2d.setColor(Color.RED);
				int i = coors.size();
				if (i >= 2) { // the same chicken-id appear
					for (int j = i - 1; j > 0; j--) { // n points <=> n-1 lines
						g2d.drawLine(coors.get(j).x + DEFAULT_SIZE / 2, coors.get(j).y + DEFAULT_SIZE / 2,
								coors.get(j - 1).x + DEFAULT_SIZE / 2, coors.get(j - 1).y + DEFAULT_SIZE / 2); // draw
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
		g2d.setColor(Color.RED);
		Dimension d1 = new Dimension(cell1X, cell1Y);
		Dimension d2 = new Dimension(cell2X, cell2Y);
		Dimension d3 = new Dimension(cell3X, cell3Y);
		Dimension d4 = new Dimension(cell4X, cell4Y);
		Dimension d5 = new Dimension(cell5X, cell5Y);
		List<Dimension> listDimen = new ArrayList<Dimension>();
		listDimen.add(d1);
		listDimen.add(d2);
		listDimen.add(d3);
		listDimen.add(d4);
		listDimen.add(d5);
		for (Dimension dm : listDimen) {
			g2d.fillOval(dm.width, dm.height, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		}

		int k = 0;
		for (Meas m : meas) {
			int disc = (int) (m.getDist() * SCALE * 2);
			g2d.setColor(Color.RED);
			g2d.drawOval(round5(listDimen.get(k).width - disc / 2), round5(listDimen.get(k).height - disc / 2), disc,
					disc);

			if (k++ == 5)
				k = 0;
		}

		g2d.setColor(Color.BLUE);
	}

	public void updateLocation(Chicken chicken) {

		System.out.println("draw chickend");
		String key = chicken.getId(); // get value of key map chicken

		// the two coordinates of a point in points Map
		int x = (int) (chicken.getCoordinates().getX() * SCALE);
		int y = (int) (chicken.getCoordinates().getY() * SCALE);

		meas = chicken.getMeas();

		// use round algorithm to fit with grid
		// constraints - make point in the middle of two lines in grid
		x = round5(x) + 500;
		y = round5(y) + 200;
		Coor coor = new Coor(x, y);
		if (points.containsKey(key)) {
			points.get(key).add(coor);
		} else {
			List<Coor> coors = new ArrayList<>();

			// check and remove if size > max_size
			// 10 steps rule
			if (coors.size() > HISTORY_POINTS) { // the 11th chicken
				coors.remove(0);
			}

			coors.add(coor);
			points.put(key, coors); // add id chicken and its coordinates
		}

		if (!colors.containsKey(key)) {
			Color idColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
					(int) (Math.random() * 255));
			colors.put(key, idColor);
		}
		repaint();
	}

	public int round5(int i) {
		// use round algorithm to fit with grid
		int XsurplusOf10 = i % 10;
		if (XsurplusOf10 != 0) {
			if (XsurplusOf10 < 5)
				i += (5 - XsurplusOf10);
			else
				i -= (XsurplusOf10 - 5);
		} else // x % 10 = 0
			i += 5;
		return i;
	}
}
