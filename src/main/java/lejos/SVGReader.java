package lejos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SVGReader {
	public String dateiname;
	public BufferedReader bufferedReader;

	public SVGReader(String dateiname) {
		InputStream in = getClass().getResourceAsStream("/" + dateiname);
		InputStreamReader inputReader = new InputStreamReader(in);
		bufferedReader = new BufferedReader(inputReader);
	}

	public String getSVGText() {
		String text = "";
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				text += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter("svginhalt.txt")));
			pWriter.println(text);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		return text;
	}
}
