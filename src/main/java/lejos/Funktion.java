package lejos;

import lejos.robotics.RegulatedMotor;

public class Funktion {

    // Eingabe der Koordinaten in mm
    public static void dreieck(int xCor, int yCor, int mmSec) {
        Motor.setSpeed(mmSec, Motor.x);
        Motor.setSpeed(mmSec, Motor.y);

        Motor.driveY(MathHelper.calcY(yCor));

        Motor.x.synchronizeWith(new RegulatedMotor[]{Motor.y});
        Motor.x.startSynchronization();

        Motor.x.rotate(MathHelper.calcX(xCor));
        Motor.y.rotate(MathHelper.calcY(-yCor));

        Motor.x.endSynchronization();

        Motor.x.waitComplete();
        Motor.y.waitComplete();
        Motor.x.rotate(MathHelper.calcX(-xCor));
    }

}
