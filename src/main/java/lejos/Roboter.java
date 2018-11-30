package lejos;

import lejos.hardware.lcd.LCD;

public class Roboter {
    public static Status status;
    public static int[][] array;

    // This method should always run first!
    // Die init Methode bringt den Druckkopf in die Startposition und begrenzt den
    // Arbeitsbereich
    public static void init() {
        changeStatus(Status.INITIALISIEREN);
        Motor.x.setSpeed(200);
        Motor.y.setSpeed(200);
        while (Sensor.pushed[0] != 1) {
            Motor.x.forward();
            Sensor.sensorMode.fetchSample(Sensor.pushed, 0);
        }
        Motor.x.stop();
        //Motor.maxRangeLeft = Motor.x.getTachoCount() - Motor.maxRangeLeft;
        //Motor.maxRangeRight = Motor.x.getTachoCount() - Motor.maxRangeRight;

        //F�hrt vorhandenes Blatt von Arbeitsfl�che
        while (Sensor.distance[0] != 0) {
            Motor.y.forward();
            Sensor.irOpen.fetchSample(Sensor.distance, 0);
        }
        Motor.y.stop();

        while (Sensor.distance[0] == 0) {
            Motor.y.backward();
            Sensor.irOpen.fetchSample(Sensor.distance, 0);
        }
        Motor.y.stop();
        Motor.y.rotate(200);

        Motor.y.resetTachoCount();
        Motor.x.resetTachoCount();
        changeStatus(Status.BETRIEBSBEREIT);
    }

    // Das Blatt wird ausgeworfen und der Druckkopf auf die Startposition gefahren
    public static void eject() {
        changeStatus(Status.ENDE);
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

    // Wechsel des Zustandes wird auf dem Display gezeigt.
    public static void changeStatus(Status s) {
        status = s;
        LCD.clear();
        LCD.drawString(status.toString(), 1, 1);
    }

    // Koordinatensystem zur Orientierung f�r den Druckkopf
    public static void koordinaten() {
        array = new int[Motor.maxRangeLeft][Motor.maxRangeBack];

    }
}
