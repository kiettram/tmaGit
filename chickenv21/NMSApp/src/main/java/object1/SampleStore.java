package object1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import com.google.gson.Gson;

import object1.observerFolder.Chicken;

public class SampleStore {
	public static void main(String[] args) {
		JFrame frame = new JFrame("frame sample store panel");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2000, 1100);
		SampleStorePanel ssp = new SampleStorePanel();
		ssp.anchor(25, 25); 	// anchor 1
		ssp.anchor(185, 25); 	// anchor 2
		ssp.anchor(25, 125); 	// anchor 3
		ssp.anchor(185, 95); 	// anchor 4
        ssp.anchor(45, 45); 	// anchor 5
		frame.add(ssp);
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;
		String line = ""; // variable used to read line by line
		try {
			File file = new File("src/main/resources/tempJson.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));
			
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser
				ssp.draw(chicken);
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
