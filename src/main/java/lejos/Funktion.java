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

    public static void driveLine(Linie line) {
        Motor.setSpeed(50, Motor.x);
        Motor.setSpeed(50, Motor.y);

        if (Motor.penUp == line.draw)
            Motor.togglePen();
        int xDiff = 0;
        int yDiff = 0;

        if (line.x >= 0)
            xDiff = Motor.x.getTachoCount() + MathHelper.calcX(line.x);
        else if (line.x < 0)
            xDiff = Motor.x.getTachoCount() - MathHelper.calcX(line.x);
        if (line.y >= 0)
            yDiff = Motor.y.getTachoCount() + MathHelper.calcY(line.y);
        else if (line.y < 0)
            yDiff = Motor.y.getTachoCount() - MathHelper.calcY(line.y);

        if (Math.abs(xDiff) < Math.abs(yDiff))
            Motor.setSpeed(Motor.x.getSpeed() * (Math.abs(xDiff) / Math.abs(yDiff)), Motor.x);
        else if (Math.abs(xDiff) > Math.abs(yDiff))
            Motor.setSpeed(Motor.y.getSpeed() * (Math.abs(yDiff) / Math.abs(xDiff)), Motor.y);

        Motor.x.synchronizeWith(new RegulatedMotor[]{Motor.y});
        Motor.x.startSynchronization();

        Motor.x.rotate(MathHelper.calcX(-xDiff));
        Motor.y.rotate(MathHelper.calcY(-yDiff));

        Motor.x.endSynchronization();

        Motor.x.waitComplete();
        Motor.y.waitComplete();
    }

    public static void driveLine(Linie[] line) {
        for (Linie l : line) {
            driveLine(l);
        }
    }

}
