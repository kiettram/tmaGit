package sample;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import object1.Chicken;

public class ChickenPanel extends JPanel {

	private static final long serialVersionUID = -2547237036084072309L;

	private static final int DEFAULT_SIZE = 10;
	private static final int S = 20;

	// neu nhu hien tai, xs la danh sach x cua tat ca cac con ga
	// -> muon moi con co 10 diem thi phai lam sao -> thiet ke lai
	// e dua tren khung nhu the nay roi apply qua ben kia xem
	// ve duoc nhu cua a vay di
	// roi them nhung thu khac vo tu tu, da
	// co hiu may cai luong nay.
	
	private List<Integer> xs = new ArrayList<>();
	private List<Integer> ys = new ArrayList<>();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		// ve x va y
		for (int i=0; i<xs.size();i++) {
			g.fillOval(xs.get(i), ys.get(i), DEFAULT_SIZE, DEFAULT_SIZE);
		}
	}

	public void draw(Chicken chicken) {

		// dung giai thuat lam tron de xac dinh chinh xac diem tren grid
		int x = (int) chicken.getCoordinates().getX() * S;
		int y = (int) chicken.getCoordinates().getY() * S;

		// add x va y
		if(xs.size()==10) {
			xs.remove(0);
		}
		xs.add(x);
		
		if(ys.size()==10) {
			ys.remove(0);
		}
		ys.add(y);
	}
}
