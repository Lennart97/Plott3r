package lejos;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class Roboter {
	public static Status status;

	// Diese Methode sollte immer als erstes aufgerufen werden
	// Die init Methode bringt den Druckkopf und das Blatt in die Startposition und begrenzt den Arbeitsbereich
	public static void init() {
		changeStatus(Status.INITIALISIEREN);
		Motor.x.setSpeed(200);
		Motor.y.setSpeed(200);

		// Positionieren des Druckkopfes
		while (Sensor.pushed[0] != 1) {
			Motor.x.forward();
			Sensor.sensorMode.fetchSample(Sensor.pushed, 0);
		}
		Motor.x.stop();

		// Fährt vorhandenes Blatt von Arbeitsfläche
		while (Sensor.distance[0] != 0) {
			Motor.y.forward();
			Sensor.irOpen.fetchSample(Sensor.distance, 0);
		}
		Motor.y.stop();

		// Registrieren und Positionieren des Blattes
		while (Sensor.distance[0] == 0) {
			Motor.y.backward();
			Sensor.irOpen.fetchSample(Sensor.distance, 0);
		}
		Motor.y.stop();
		Motor.y.rotate(200);

		Motor.y.resetTachoCount();
		Motor.x.resetTachoCount();
		changeStatus(Status.BETRIEBSBEREIT);
		Sound.beepSequenceUp();
	}

	// Das Blatt wird ausgeworfen und der Druckkopf auf die Startposition gefahren
	public static void eject() {
		changeStatus(Status.ENDE);
		Sound.beepSequence();
		if (!Motor.penUp)
			Motor.togglePen();
		Motor.x.setSpeed(200);
		Motor.y.setSpeed(600);
		Motor.y.rotate(-3000);
		while (Sensor.pushed[0] != 1) {
			Motor.x.forward();
			Sensor.sensorMode.fetchSample(Sensor.pushed, 0);
		}
		Motor.x.stop();
	}

	// Wechsel des Zustandes wird auf dem Display ausgegeben
	public static void changeStatus(Status s) {
		status = s;
		LCD.clear();
		LCD.drawString(status.toString(), 1, 1);
	}

}
