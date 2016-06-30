package sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

import com.google.gson.Gson;

public class StartButton implements ActionListener{

	
	private ChickenPanel panel;
	
	public  StartButton(ChickenPanel panel) {
		this.panel=panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		readDataFile();
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
//				panel.repaint();
//				mainFrame.repaint();
				
				Thread.sleep(1000); // sleep in millisecond

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

}
