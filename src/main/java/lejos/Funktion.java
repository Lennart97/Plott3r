package lejos;

import lejos.robotics.RegulatedMotor;

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


        Motor.y.synchronizeWith(new RegulatedMotor[]{Motor.x});
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

    public static Linie[] StringToLines(String text) {

        ArrayList<Linie> lines = new ArrayList<Linie>();
        String[] values = text.split(" ");
        for (int i = 0; i < values.length - 1; i += 2) {
            lines.add(new Linie(Math.round(Float.valueOf(values[i])), Math.round(Float.valueOf(values[i + 1])), true));
        }
        lines.get(0).draw = false;
        return lines.toArray(new Linie[lines.size()]);

    }

}
