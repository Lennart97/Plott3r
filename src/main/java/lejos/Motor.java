package lejos;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Motor {
	// die 3 Motoren des Plotters
	public static RegulatedMotor z = new EV3LargeRegulatedMotor(MotorPort.A);
	public static RegulatedMotor x = new EV3LargeRegulatedMotor(MotorPort.B);
	public static RegulatedMotor y = new EV3LargeRegulatedMotor(MotorPort.C);

	public static int maxRangeLeft = -1600;
	public static int maxRangeRight = 0;
	public static int maxRangeFront = 0;
	public static int maxRangeBack = -2300;

	// Gibt an ob der Stift oben ist
	public static boolean penUp = true;

	// Setzt die Geschwindigkeit in mm pro Sekunde fuer die x und y Achse
	public static void setSpeed(int mmSec, RegulatedMotor motor) {
		if (motor.equals(x)) {
			x.setSpeed(MathHelper.degreeX(mmSec));
		} else if (motor.equals(y)) {
			y.setSpeed(MathHelper.degreeY(mmSec));
		}
	}

	// Verfahren der x-Achse, bei eingabe eines Wertes innerhalb des
	// Arbeitsbereiches
	public static void driveX(int degree) {
		if (degree + x.getTachoCount() >= maxRangeLeft && degree + x.getTachoCount() <= maxRangeRight) {
			x.rotate(degree);
		}
	}

	// Overload to set direction by String, rather than negation
	public static void driveX(int degree, String direction) {
		if (direction.equals("l")) {
			degree = -degree;
		}
		if (degree + x.getTachoCount() >= maxRangeLeft && degree + x.getTachoCount() <= maxRangeRight) {
			x.rotate(degree);
		}
	}

	// Verfahren der y-Achse, bei eingabe eines Wertes innerhalb des
	// Arbeitsbereiches
	public static void driveY(int degree) {
		if (degree + y.getTachoCount() <= maxRangeFront && degree + y.getTachoCount() >= maxRangeBack) {
			y.rotate(degree);
		}
	}

	// Overload to set direction by String, rather than negation
	public static void driveY(int degree, String direction) {
		if (direction.equals("f")) {
			degree = -degree;
		}
		if (degree + y.getTachoCount() <= maxRangeFront && degree + y.getTachoCount() >= maxRangeBack) {
			y.rotate(degree);
		}
	}

	// Vor dem Betrieb muss sich der Stift in der hoechsten Position befinden!
	// Wechselt vom letzten Zustand (oben/unten) zum naechsten
	public static void togglePen() {
		penUp = penUp != true;
		z.rotate(180);
	}

}
