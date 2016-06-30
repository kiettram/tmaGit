package sample;

/*
 * coordinates is a chicken's component class 
 */
public class Coordinates extends Chicken{

	private double x;
	private double y; 
	private double z;
	private double heading;
	
	Coordinates() {}
	
	Coordinates(double x, double y, double z, double heading) {
		
		super(); // was used to invoke Constructor of the nearest class which it inherited
		this.x = x;
		this.y = y;
		this.z = z;
		this.heading = heading;
		
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}

	@Override
	public String toString() {
		return "coordinates [x=" + x + ", y=" + y + ", z=" + z + ", heading=" + heading + "]";
	}
	
	
}
