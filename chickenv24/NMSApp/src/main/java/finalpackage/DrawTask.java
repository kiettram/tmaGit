package finalpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTask extends JFrame implements Task {
	private static final int FRAME_WIDTH_SIZE = 2000;
	private static final int FRAME_HIGH_SIZE = 1100;
	private JPanel gridPanel; // panel used to contain grid was drawn from

	private static final long serialVersionUID = 1L;
	private static final int SCALE = 10;
	private static final int HISTORY_POINTS = 10;
	private DrawFunction drawFuntion = new DrawFunction();
	private static Map<String, List<Coor>> points = new HashMap<String, List<Coor>>();
	private static Map<String, Color> colors = new HashMap<String, Color>();
	private static List<Meas> meas = new ArrayList<Meas>();
	
	public static Map<String, List<Coor>> getPoints() {
		return points;
	}

	public void setPoints(Map<String, List<Coor>> points) {
		DrawTask.points = points;
	}

	public static Map<String, Color> getColors() {
		return colors;
	}

	public void setColors(Map<String, Color> colors) {
		DrawTask.colors = colors;
	}

	public static List<Meas> getMeas() {
		return meas;
	}

	public void setMeas(List<Meas> meas) {
		DrawTask.meas = meas;
	}

	public DrawTask() {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HIGH_SIZE);
		// draw grid panel
		gridPanel = new JPanel();
		gridPanel.setLayout(new BorderLayout());
		gridPanel.setBackground(Color.PINK);
		gridPanel.add(drawFuntion, BorderLayout.CENTER);
		this.add(gridPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null); // show frame in the monitor's
		this.setVisible(true); // show frame
	}

	@Override
	public void update(Chicken chicken) {
		
		System.out.println("draw chickend");
		String key = chicken.getId(); // get value of key map chicken

		// the two coordinates of a point in points Map
		int x = (int) (chicken.getCoordinates().getX() * SCALE);
		int y = (int) (chicken.getCoordinates().getY() * SCALE);

		DrawTask.meas = chicken.getMeas();

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
