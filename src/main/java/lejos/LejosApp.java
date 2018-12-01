package lejos;
@author ViiRoX
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
        Motor.togglePen();

        // hier drucken
        //Funktion.dreieck(-100, -100, 60);

        Motor.driveY(200, "f");

        Roboter.eject();
    }

}