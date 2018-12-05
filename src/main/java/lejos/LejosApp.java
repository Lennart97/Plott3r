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
    // z.B.: driveX(500, left) oder in mm mit: driveY(degreeY(800), forward)

	public static void main(String[] args) {
		Roboter.init();
		Roboter.changeStatus(Status.DRUCKEN);


        // hier drucken

		/*Cedric
		Funktion.driveLine(Funktion.StringToLines("31.1758 23.1163 22.1743 23.1163 22.4391 30.2646 31.7053 29.9998"));
        Funktion.driveLine(Funktion.StringToLines("56.1636 23.9096 55.8988 23.9096 47.4268 24.1744 52.9866 27.6161 47.6916 31.3226 56.1636 31.3226"));
        Funktion.driveLine(Funktion.StringToLines("74.29 28.3274 74.29 37.3289 82.762 33.0929 74.29 28.3274"));
        Funktion.driveLine(Funktion.StringToLines("94.3119 34.622 94.3119 25.0909 99.6069 28.5327 94.8414 30.1212 99.3422 34.622"));
        Funktion.driveLine(Funktion.StringToLines("122.58 24.57 122.58 34.28"));
        Funktion.driveLine(Funktion.StringToLines("153.921 24.3445 146.243 24.6093 145.714 32.0223 153.392 31.8557"));*/

        //Lennart
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));
        Funktion.driveLine(Funktion.StringToLines(""));

		Roboter.eject();
	}

}