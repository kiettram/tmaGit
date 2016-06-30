package sample;

/*
 * meas list class is a chicken's component class 
 */
public class Meas extends Chicken{

	private String anchor;
	private double dist;
	private double dqf;
	private double tqf;
	
	Meas() {}
	
	Meas(String anchor, double dist, double dqf, double tqf) {
		
		super(); // was used to invoke Constructor of the nearest class which it inherited
		this.anchor = anchor;
		this.dist = dist;
		this.dqf = dqf;
		this.tqf = tqf;
		
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public double getDqf() {
		return dqf;
	}

	public void setDqf(double dqf) {
		this.dqf = dqf;
	}

	public double getTqf() {
		return tqf;
	}

	public void setTqf(double tqf) {
		this.tqf = tqf;
	}

	@Override
	public String toString() {
		return "meas [anchor=" + anchor + ", dist=" + dist + ", dqf=" + dqf + ", tqf=" + tqf + "]";
	}
	
	
	
	
}
