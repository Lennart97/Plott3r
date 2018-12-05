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
        Motor.setSpeed(10, Motor.x);
        Motor.setSpeed(10, Motor.y);

		if (Motor.penUp == line.draw)
			Motor.togglePen();
        int xMove = MathHelper.mmX(Math.abs(Motor.x.getTachoCount())) - line.x;
        int yMove = MathHelper.mmX(Math.abs(Motor.y.getTachoCount())) - line.y;
		int xDiff = Math.abs(xMove);
		int yDiff = Math.abs(yMove);


        double speed = 0;
        if (xDiff < yDiff) {
            speed = (Motor.getSpeed(Motor.x) * (xDiff / yDiff));
            Motor.setSpeed((int) speed, Motor.x);
        } else if (xDiff > yDiff) {
            System.out.println("SPeed Y" + Motor.getSpeed(Motor.y));
            double diff = yDiff / xDiff;
            speed = (Motor.getSpeed(Motor.y) * diff);
            Motor.setSpeed((int) speed, Motor.y);
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

}
