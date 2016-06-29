package sample;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;

import com.google.gson.Gson;

public class App extends JFrame {

	private static final long serialVersionUID = -2305254065268600465L;
	private static final int size = 1500;

	private ChickenPanel panel = new ChickenPanel();

	public App() {
		setVisible(true);
		setSize(size, size);
		setLocationRelativeTo(null);

		add(panel);
	}

	public void readDataFile() {
		Gson gson = new Gson();
		FileInputStream fis = null;
		BufferedReader br = null;

		try {

			File file = new File("src/main/resources/json_file.raw");
			fis = new FileInputStream(file); // Open File Input Stream
			br = new BufferedReader(new InputStreamReader(fis));

			String line; // variable used to read line by line
			while ((line = br.readLine()) != null) {
				Chicken chicken = gson.fromJson(line, Chicken.class); // parser

				panel.draw(chicken);
				panel.repaint();
				
				Thread.sleep(500); // sleep in millisecond

			}

		} catch (Exception e) {
			// ignore
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

	public static void main(String[] args) {
		App app = new App();
		app.readDataFile();
	}
}
