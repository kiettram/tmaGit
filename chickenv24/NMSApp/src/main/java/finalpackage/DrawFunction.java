package finalpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private static final int WIDTH_RECTANGLE = 1850;
	private static final int HIGH_RECTANGLE = 1050;
	private Map<String, List<Coor>> points = DrawTask.getPoints();
	private Map<String, Color> colors = DrawTask.getColors();
	private List<Meas> meas = DrawTask.getMeas();
	
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

		Set<String> keys = points.keySet();
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
		g2d.fillOval(cell1X, cell1Y, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		g2d.fillOval(cell2X, cell2Y, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		g2d.fillOval(cell3X, cell3Y, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		g2d.fillOval(cell4X, cell4Y, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		g2d.fillOval(cell5X, cell5Y, DEFAULT_SIZE * 2, DEFAULT_SIZE * 2);
		for (Meas m : meas) {
			int disc = (int) (m.getDist() * SCALE * 2);
			g2d.setColor(Color.RED);
			g2d.drawOval(round5(cell1X - disc / 2), round5(cell1Y - disc / 2), disc, disc);
			g2d.drawOval(round5(cell2X - disc / 2), round5(cell2Y - disc / 2), disc, disc);
			g2d.drawOval(round5(cell3X - disc / 2), round5(cell3Y - disc / 2), disc, disc);
			g2d.drawOval(round5(cell4X - disc / 2), round5(cell4Y - disc / 2), disc, disc);
			g2d.drawOval(round5(cell5X - disc / 2), round5(cell5Y - disc / 2), disc, disc);
			repaint();
		}
		g2d.setColor(Color.BLUE);
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
