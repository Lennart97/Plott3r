package lejos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SVGReader {
	public String dateiname;
	public BufferedReader bufferedReader;

	public SVGReader(String dateiname) {
		InputStream in = getClass().getResourceAsStream("/" + dateiname);
		InputStreamReader inputReader = new InputStreamReader(in);
		bufferedReader = new BufferedReader(inputReader);
	}

	public String[] getSVGText() {
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines.toArray(new String[lines.size()]);
	}
}
