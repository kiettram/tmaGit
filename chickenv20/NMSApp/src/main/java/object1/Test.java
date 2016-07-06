package object1;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Frame test");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(2000, 1100);
		
		DrawPoints dr = new DrawPoints();
		frame.add(dr);
		dr.repaint();
		
		
		
		
		
		for (int i = 0 ; i <= 1000 ; i+=10) {
			
			
			
			if (dr.getxList().size() == 10) {
				dr.getxList().remove(0);
				dr.getyList().remove(0);
			}
			
			
			
			dr.addX(i);
			dr.addY(i);
			
			
			dr.repaint();
			
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}
