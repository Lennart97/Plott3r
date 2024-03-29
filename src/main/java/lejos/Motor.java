package lejos;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Motor {
	// die 3 Motoren des Plotters
	public static RegulatedMotor z = new EV3LargeRegulatedMotor(MotorPort.A);
	public static RegulatedMotor x = new EV3LargeRegulatedMotor(MotorPort.B);
	public static RegulatedMotor y = new EV3LargeRegulatedMotor(MotorPort.C);

	// maximal zulässige Gradzahlen die die Motoren fahren dürfen um im gültigen Bereich zu bleiben
    public static int maxRangeLeft = -1600;
	public static int maxRangeRight = 0;
	public static int maxRangeFront = 0;
	public static int maxRangeBack = -2000;

	// Gibt an ob der Stift oben ist
	public static boolean penUp = true;

	// Setzt die Geschwindigkeit in mm pro Sekunde für die x und y Achse
	public static void setSpeed(int mmSec, RegulatedMotor motor) {
		if (motor.equals(x)) {
			x.setSpeed(MathHelper.degreeX(mmSec));
		} else if (motor.equals(y)) {
			y.setSpeed(MathHelper.degreeY(mmSec));
		}
	}

    // gibt Geschwindigkeit in mmSec zurück
    public static int getSpeed(RegulatedMotor motor) {
        int speed = 0;
        if (motor.equals(x)) {
            speed = MathHelper.mmX(motor.getSpeed());
        } else if (motor.equals(y)) {
            speed = MathHelper.mmY(motor.getSpeed());
        }
        return speed;
    }

	// Verfahren der x-Achse, bei eingabe eines Wertes innerhalb des Arbeitsbereiches
	public static void driveX(int degree) {
		if (degree + x.getTachoCount() >= maxRangeLeft && degree + x.getTachoCount() <= maxRangeRight) {
			x.rotate(degree);
		}
	}

	// Verfahren der y-Achse, bei eingabe eines Wertes innerhalb des Arbeitsbereiches
	public static void driveY(int degree) {
		if (degree + y.getTachoCount() <= maxRangeFront && degree + y.getTachoCount() >= maxRangeBack) {
			y.rotate(degree);
		}
	}

	// Vor dem Betrieb muss sich der Stift in der höchsten Position befinden!
	// Wechselt vom letzten Zustand (oben/unten) zum nächsten
	public static void togglePen() {
		penUp = !penUp;
		z.rotate(180);
	}

}
