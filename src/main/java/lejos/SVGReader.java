package lejos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SVGReader {
	public String dateiname;
	public BufferedReader bufferedReader;

	public SVGReader(String dateiname) {
		InputStream in = getClass().getResourceAsStream("/" + dateiname);
		InputStreamReader inputReader = new InputStreamReader(in);
		bufferedReader = new BufferedReader(inputReader);
	}

	public void getSVGText() {
		String text = "";
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
