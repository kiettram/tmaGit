package finalpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		ChickenObserver obj = new ChickenObserver();
		Task draw = new DrawTask();
		obj.registerTask(draw);

		// Task send = (Task) new SendTask();
		// obj.registerTask(send);

		// Chicken a = new Chicken();
		// obj.notifyUpdate(a);
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			File file = new File("src/main/resources/tempJson.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));

			String line = ""; // variable used to read line by line
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				obj.notifyUpdate(chicken); // update all task
				Thread.sleep(1000); // sleep in millisecond
			}
		} catch (Exception e) {
		} finally {
			try {
				fis.close(); // Close File Input Stream
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close(); // Close Buffered Reader
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
