package upd.dev;

public class Methods {
    public static String randomRange(int min, int max) {
        double rnd = Math.random();
        return String.valueOf(min + (int)(rnd * ((max - min) + 1)));
    }
}
