package lejos;

public class MathHelper {

    // Umrechnung von mm in grad fuer x-Achse
    public static int degreeX(int mm) {
        int degree = (int) (mm * 9.009);
        return degree;
    }

    // Umrechnung von mm in grad fuer y-Achse
    public static int degreeY(int mm) {
        int degree = (int) (mm * 8.196);
        return degree;
    }

    // Umrechnung von grad in mm fuer x-Achse
    public static int mmX(int degree) {
        int mm = (int) (degree / 9.009);
        return mm;
    }

    // Umrechnung von grad in mm fuer y-Achse
    public static int mmY(int degree) {
        int mm = (int) (degree / 8.196);
        return mm;
    }

}
