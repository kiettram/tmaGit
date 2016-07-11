package finalpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTask extends JFrame implements Task {
	private static final int FRAME_WIDTH_SIZE = 2000;
	private static final int FRAME_HIGH_SIZE = 1100;
	private JPanel gridPanel; // panel used to contain grid was drawn from
	private static final long serialVersionUID = 1L;
	private DrawFunction drawFunction = new DrawFunction();

	public DrawTask() {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(FRAME_WIDTH_SIZE, FRAME_HIGH_SIZE);
		// draw grid panel
		gridPanel = new JPanel();
		gridPanel.setLayout(new BorderLayout());
		gridPanel.setBackground(Color.PINK);
		gridPanel.add(drawFunction, BorderLayout.CENTER);
		this.add(gridPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null); // show frame in the monitor's
		this.setVisible(true); // show frame
	}

	@Override
	public void update(Chicken chicken) {

		drawFunction.updateLocation(chicken);
		
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
