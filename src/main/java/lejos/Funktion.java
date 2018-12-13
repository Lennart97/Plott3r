package lejos;

import lejos.robotics.RegulatedMotor;

import java.util.ArrayList;

public class Funktion {

	// nähert eine Kreisform an
	public static void drawCircle(int x, int y, int radius) {
		Motor.driveX(MathHelper.degreeX(-x));
		Motor.driveY(MathHelper.degreeY(-y));
		String kreis = "";
		double winkel = 2 * Math.PI / 90;
		for (int i = 1; i <= 90; i++) {
			double xCor = (Math.cos(i * winkel) * radius) + x;
			double yCor = (Math.sin(i * winkel) * radius) + y;
			kreis += xCor + " " + yCor + " ";
			winkel += 2 * Math.PI / 90;
		}
		driveLine(stringToLines(kreis));
	}

	// Fährt eine Linie anhand der Koordination in Grad ab
	public static void driveLine(Linie line) {
		Motor.x.setSpeed(200);
		Motor.y.setSpeed(200);

		if (Motor.penUp == line.draw)
			Motor.togglePen();

		int xMove = Math.abs(Motor.x.getTachoCount()) - line.x;
		int yMove = Math.abs(Motor.y.getTachoCount()) - line.y;
		float xDiff = Math.abs(xMove);
		float yDiff = Math.abs(yMove);

		float speed;
		if (xDiff < yDiff) {
			speed = (Motor.x.getSpeed() / (yDiff / xDiff));
			Motor.x.setSpeed(Math.round(speed));
		} else if (xDiff > yDiff) {
			speed = (Motor.y.getSpeed() / (xDiff / yDiff));
			Motor.y.setSpeed(Math.round(speed));
		}

		Motor.y.synchronizeWith(new RegulatedMotor[] { Motor.x });
		Motor.y.startSynchronization();

		Motor.x.rotate(xMove);
		Motor.y.rotate(yMove);

		Motor.y.endSynchronization();

		Motor.x.waitComplete();
		Motor.y.waitComplete();
	}

	// Fährt mehrere Linien anhand der Koordinaten in Grad ab
	public static void driveLine(Linie[] line) {
		for (Linie l : line) {
			driveLine(l);
		}
	}

	// Erzeugt Linienobjekte mit den entsprechenden Koordinaten aus dem Übergebenen
	// Text
	// Der übergebene Text muss nur aus mit Leerzeichen voneinander getrennten
	// Koordinaten bestehen
	public static Linie[] stringToLines(String text) {
		ArrayList<Linie> lines = new ArrayList<Linie>();
		String[] values = text.split(" ");
		for (int i = 0; i < values.length - 1; i += 2) {
			lines.add(new Linie(Math.round(Float.valueOf(values[i])), Math.round(Float.valueOf(values[i + 1])), true));
		}
		lines.get(0).draw = false;
		return lines.toArray(new Linie[lines.size()]);
	}

	// Nimmt eine SVG-Datei in Form eines String-Arrays entgegen und zeichnet dieses
	// Ein Objekt der Klasse SVGReader liefert das geeignete Zeilenbasierte Format welches diese Methode benötigt
	// SVG's müssen im Format 1600px * 2000px erstellt werden
	public static void drawSVG(String[] lines) {
		for (String line : lines) {
			if (line.contains("width") && line.contains("height") && !line.contains("<rect")) {
				// Prüfen ob die Maße des SVG stimmen
				if (!(line.contains("width=\"1600px\"") && line.contains("height=\"2000px\""))) {
					try {
						throw new Exception("Falsche SVG-Groesse oder Einheit");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (line.contains("<rect") && !line.contains("<rect id=\"svgEditorBackground\"")) {
				// Wenn ein Rechteck gezeichnet werden soll
				String[] xTeil = line.split("x=\"");
				String[] x = xTeil[1].split("\"");
				String[] yTeil = line.split("y=\"");
				String[] y = yTeil[1].split("\"");
				String[] widthTeil = line.split("width=\"");
				String[] width = widthTeil[1].split("\"");
				String[] heightTeil = line.split("height=\"");
				String[] height = heightTeil[1].split("\"");
				float xWert = Float.valueOf(x[0]) + Float.valueOf(width[0]);
				float yWert = Float.valueOf(y[0]) + Float.valueOf(height[0]);

				String rectangle = x[0] + " " + y[0] + " " + xWert + " " + y[0] + " " + xWert + " " + yWert + " " + x[0]
						+ " " + yWert + " " + x[0] + " " + y[0];
				driveLine(stringToLines(rectangle));
			} else if (line.contains("<polyline")) { // Wenn eine Polyline gezeichnet werden soll
				String[] punkte = line.split("points=\"");
				line = punkte[punkte.length - 1];
				String[] a = line.split("\"");
				driveLine(stringToLines(a[0]));
			} else if (line.contains("<line")) {
				// Wenn eine Linie gezeichnet werden soll
				String[] punkt = line.split("x1=\"");
				String[] x1 = punkt[punkt.length - 1].split("\" y1=\"");
				String[] y1 = x1[x1.length - 1].split("\" x2=\"");
				String[] x2 = y1[y1.length - 1].split("\" y2=\"");
				String[] y2 = x2[x2.length - 1].split("\"");

				String linie = x1[0] + " " + y1[0] + " " + x2[0] + " " + y2[0];
				driveLine(stringToLines(linie));
			} else if (line.contains("<polygon")) {
				// Wenn ein Polygon gezeichnet werden soll
				String[] punkte = line.split("points=\"");
				String[] nurPunkte = punkte[punkte.length - 1].split("\"");
				String[] coordinates = nurPunkte[0].split(" ");
				String polygon = nurPunkte[0] + " " + coordinates[0] + " " + coordinates[1];
				driveLine(stringToLines(polygon));
			}
		}
	}

	// Initialen LH CM
	public static void signature() {
		// L
		Funktion.driveLine(Funktion.stringToLines("70.0 0.0 0.0 0.0 0.0 40.0 "));

		// H
		Funktion.driveLine(Funktion.stringToLines("70 60 0 60"));
		Funktion.driveLine(Funktion.stringToLines("70 100 0 100"));
		Funktion.driveLine(Funktion.stringToLines("35 60 35 100"));

		// C
		Funktion.driveLine(Funktion.stringToLines("60 30 60 0 0 0 0 30"));
		// M
		Funktion.driveLine(Funktion.stringToLines("0 50 60 70 30 80 60 90 0 110"));
	}

}
