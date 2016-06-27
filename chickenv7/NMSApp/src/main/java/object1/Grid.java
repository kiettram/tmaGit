package object1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/*
 * grid component here and 5 anchors configure
 */
@SuppressWarnings("serial")
public class Grid extends JPanel{

    private List<Point> anchors;
    
    private int w = Preparation.WIDTH_RECTANGLE;
    private int h = Preparation.HIGH_RECTANGLE;
    
    private int wOval = 200;
    private int hOval = 200;
    
//    private int i = 1;

    public Grid() {
    	anchors = new ArrayList<>(25);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Point anchor : anchors) {
            int cellX = 10 + (anchor.x * 10);
            int cellY = 10 + (anchor.y * 10);
            g.setColor(Color.RED);
            g.fillRect(cellX, cellY, 10, 10);
            g.drawOval(cellX-wOval/2, cellY-hOval/2, wOval, hOval);
            g.setColor(Color.BLUE);
            g.drawString("anchor " + anchor, cellX, cellY);
//            i++;
//            if (i > 5) i=0;
        }
        
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, w, h);

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
}
