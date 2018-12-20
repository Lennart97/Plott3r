package lejos;

public class MathHelper {

    // Umrechnung von mm in grad für x-Achse
    public static int degreeX(int mm) {
        int degree = (int) (mm * 9.009);
        return degree;
    }

    // Umrechnung von mm in grad für y-Achse
    public static int degreeY(int mm) {
        int degree = (int) (mm * 8.196);
        return degree;
    }

    // Umrechnung von grad in mm für x-Achse
    public static int mmX(int degree) {
        int mm = Math.round(degree / 9.009f);
        return mm;
    }

    // Umrechnung von grad in mm für y-Achse
    public static int mmY(int degree) {
        int mm = Math.round(degree / 8.196f);
        return mm;
    }

}
