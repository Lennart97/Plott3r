package lejos;

//@author ViiRoX, Lennart97
public class LejosApp {

	// Z-Druckkopf = Motorport A
	// X-Druckkopf = Motorport B
	// Y-Blattvorlauf = Motorport C
	// Drucksensor = S1
	// Optischer Sensor = S2
	// x positiv = Rechts ->
	// x negativ = Links <-
	// y positiv = Hinten /\
	// y negativ = Vorne \/
	// Die Richtung der Achsen kann den drive Methoden auch als String mitgegeben
	// werden.
	// Dabei wird immer ein positiver Wert als degree und die Richtung �bergeben.
	// (left, right, forward, backward)
	// z.B.: driveX(500, left) oder in mm mit: driveY(calcY(800), forward)

	public static void main(String[] args) {
		Roboter.init();
		Roboter.changeStatus(Status.DRUCKEN);

		// hier drucken
		Motor.togglePen();
		Funktion.dreieck(-100, -100, 200);
		Motor.togglePen();
		//Motor.driveY(MathHelper.calcY(-200));
		//Motor.togglePen();
		//Funktion.dreieck(-50, -50, 10);
		//Motor.togglePen();

		Roboter.eject();
	}

}