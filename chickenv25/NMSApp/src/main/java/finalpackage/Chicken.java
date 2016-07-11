package finalpackage;

import java.util.List;

/*
 * chicken class, class mapped with json file 
 */
public class Chicken {

	private String id;
	private Coordinates coordinates;
	private String time;
	private String timestamp;
	private List<Meas> meas;

	Chicken() {
	}

	Chicken(String id, Coordinates coo, String time, String timestamp, List<Meas> meas) {

		this.id = id;
		this.coordinates = coo;
		this.time = time;
		this.timestamp = timestamp;
		this.meas = meas;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<Meas> getMeas() {
		return meas;
	}

	public void setMeas(List<Meas> meas) {
		this.meas = meas;
	}

	@Override
	public String toString() {
		return "Chicken [id=" + id + ", coo=" + coordinates + ", time=" + time + ", timestamp=" + timestamp + ", meas="
				+ meas + "]";
	}

}
