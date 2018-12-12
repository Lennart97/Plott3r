package lejos;

//@author ViiRoX, Lennart97
public class LejosApp {

	/*
	Z-Druckkopf = Motorport A
	X-Druckkopf = Motorport B
	Y-Blattvorlauf = Motorport C
	Drucksensor = S1
	Optischer Sensor = S2
	x positiv = Rechts ->
	x negativ = Links <-
	y positiv = Hinten /\
	y negativ = Vorne \/
	Die Richtung der Achsen kann den drive Methoden auch als String mitgegeben
	werden.
	Dabei wird immer ein positiver Wert als degree und die Richtung uebergeben.
	(left, right, forward, backward)
    z.B.: driveX(500, left) oder in mm mit: driveY(degreeY(800), forward)
    Max range in mm for x = 175 and y = 240
    */

	public static void main(String[] args) {
		Roboter.init();
		Roboter.changeStatus(Status.DRUCKEN);

        // hier drucken
		SVGReader test = new SVGReader("test.svg");
		Funktion.drawSVG(test.getSVGText());

		Roboter.eject();
	}

}