package lejos;

import lejos.robotics.RegulatedMotor;

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
		Motor.setSpeed(50, Motor.x);
		Motor.setSpeed(50, Motor.y);

		if (Motor.penUp == line.draw)
			Motor.togglePen();
        int xMove = Math.abs(Motor.x.getTachoCount()) - MathHelper.degreeX(line.x);
        int yMove = Math.abs(Motor.y.getTachoCount()) - MathHelper.degreeY(line.y);
		int xDiff = Math.abs(xMove);
		int yDiff = Math.abs(yMove);

		if (xDiff < yDiff)
			Motor.setSpeed(Motor.x.getSpeed() * (xDiff / yDiff), Motor.x);
		else if (xDiff > yDiff)
			Motor.setSpeed(Motor.y.getSpeed() * (yDiff / xDiff), Motor.y);

        Motor.y.synchronizeWith(new RegulatedMotor[]{Motor.x});
        Motor.y.startSynchronization();


        Motor.x.rotate(xMove);
        Motor.y.rotate(yMove);

        Motor.y.endSynchronization();

		Motor.x.waitComplete();
		Motor.y.waitComplete();
	}

	public static void driveLine(Linie[] line) {
		for (Linie l : line) {
			driveLine(l);
		}
	}

}
