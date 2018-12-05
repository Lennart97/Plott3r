package lejos;

public class MathHelper {

    // Umrechnung von mm in grad fuer x-Achse
    public static int calcX(int mm) {
        int degree = (int) (mm * 9.009);
        return degree;
    }

    // Umrechnung von mm in grad fuer y-Achse
    public static int calcY(int mm) {
        int degree = (int) (mm * 8.196);
        return degree;
    }

}
