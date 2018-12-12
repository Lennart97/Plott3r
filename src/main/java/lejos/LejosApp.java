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

    Max range in degree for x = 1600 and y = 2000
    */

	public static void main(String[] args) {
		Roboter.init();
		Roboter.changeStatus(Status.DRUCKEN);

        // hier drucken
		SVGReader test = new SVGReader("test.svg");
		Funktion.drawSVG(test.getSVGText());

        SVGReader svg = new SVGReader("drawsvg.svg");
        Funktion.drawSVG(svg.getSVGText());

		Roboter.eject();
	}

}