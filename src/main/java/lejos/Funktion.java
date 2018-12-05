package lejos;

import lejos.robotics.RegulatedMotor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Funktion {

	// Eingabe der Koordinaten in mm
	public static void dreieck(int xCor, int yCor, int mmSec) {
		Motor.setSpeed(mmSec, Motor.x);
		Motor.setSpeed(mmSec, Motor.y);

		Motor.driveY(MathHelper.degreeY(yCor));

		Motor.x.synchronizeWith(new RegulatedMotor[] { Motor.y });
		Motor.x.startSynchronization();

		Motor.x.rotate(MathHelper.degreeX(xCor));
		Motor.y.rotate(MathHelper.degreeY(-yCor));

		Motor.x.endSynchronization();

		Motor.x.waitComplete();
		Motor.y.waitComplete();
		Motor.x.rotate(MathHelper.degreeX(-xCor));
	}

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
        System.out.println(kreis);
        driveLine(stringToLines(kreis));
	}

	public static void driveLine(Linie line) {
		Motor.setSpeed(20, Motor.x);
		Motor.setSpeed(20, Motor.y);

		if (Motor.penUp == line.draw)
			Motor.togglePen();
		int xMove = MathHelper.mmX(Math.abs(Motor.x.getTachoCount())) - line.x;
		int yMove = MathHelper.mmX(Math.abs(Motor.y.getTachoCount())) - line.y;
		float xDiff = Math.abs(xMove);
		float yDiff = Math.abs(yMove);

		float speed = 0;
		if (xDiff < yDiff) {
			speed = (Motor.getSpeed(Motor.x) / (yDiff / xDiff));
			Motor.setSpeed(Math.round(speed), Motor.x);
		} else if (xDiff > yDiff) {
			speed = (Motor.getSpeed(Motor.y) / (xDiff / yDiff));
			Motor.setSpeed(Math.round(speed), Motor.y);
		}

		Motor.y.synchronizeWith(new RegulatedMotor[] { Motor.x });
		Motor.y.startSynchronization();

		Motor.x.rotate(MathHelper.degreeX(xMove));
		Motor.y.rotate(MathHelper.degreeY(yMove));

		Motor.y.endSynchronization();

		Motor.x.waitComplete();
		Motor.y.waitComplete();
	}

	public static void driveLine(Linie[] line) {
		for (Linie l : line) {
			driveLine(l);
		}
	}

	public static Linie[] stringToLines(String text) {

		ArrayList<Linie> lines = new ArrayList<Linie>();
		String[] values = text.split(" ");
		for (int i = 0; i < values.length - 1; i += 2) {
			lines.add(new Linie(Math.round(Float.valueOf(values[i])), Math.round(Float.valueOf(values[i + 1])), true));
		}
		lines.get(0).draw = false;
		return lines.toArray(new Linie[lines.size()]);

	}

    public static void drawSVG(String name) {
        Linie[] lines = null;

        try {
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            String zeile = br.readLine();
            zeile = br.readLine();

            if (!(zeile.contains("width=\"175mm\"") && zeile.contains("height=\"275mm\""))) {
                try {
                    throw new Exception("Falsche SVG-Groesse");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            zeile = br.readLine();
            while (zeile != null) {
                //
                // 1. rect muss ignoriert werden!!
                //
                if (zeile.contains("<rect")) {
                    String[] xTeil = zeile.split("x=\"");
                    String[] x = xTeil[1].split("\"");
                    String[] yTeil = zeile.split("y=\"");
                    String[] y = yTeil[1].split("\"");
                    String[] widthTeil = zeile.split("width=\"");
                    String[] width = widthTeil[1].split("\"");
                    String[] heightTeil = zeile.split("height=\"");
                    String[] height = heightTeil[1].split("\"");
                    float xWert = Float.valueOf(x[0]) + Float.valueOf(width[0]);
                    float yWert = Float.valueOf(y[0]) + Float.valueOf(height[0]);

                    zeile = x[0] + " " + y[0] + " " + xWert + " " + y[0] + " " + xWert + " " + yWert + " " + x[0] + " "
                            + yWert + " " + x[0] + " " + y[0];
                    driveLine(stringToLines(zeile));
                } else if (zeile.contains("<polyline")) {
                    String[] punkte = zeile.split("points=\"");
                    zeile = punkte[punkte.length - 1];
                    String[] a = zeile.split("\"");
                    zeile = a[0];
                    driveLine(stringToLines(zeile));
                } else if (zeile.contains("<line")) {

                } else if (zeile.contains("<polygon")) {

                }
                zeile = br.readLine();

            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
