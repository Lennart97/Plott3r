package lejos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SVGReader {
	// Ist im Format 'name.svg' anzugeben
	public String dateiname;

	public SVGReader(String dateiname) {
		this.dateiname = dateiname;
	}
	
	// Gibt den Text der SVG-Datei in einem String-Array zurück.
	// SVG-Dateien müssen vorher mit Programmen wie z.B. Visual Code formatiert werden!
	public String[] getSVGText() {
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try {
			InputStream in = getClass().getResourceAsStream("/" + dateiname);
			InputStreamReader inputReader = new InputStreamReader(in);
			BufferedReader bufferedReader = new BufferedReader(inputReader);
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line + "\n");
			}
			bufferedReader.close();
			inputReader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines.toArray(new String[lines.size()]);
	}
}
