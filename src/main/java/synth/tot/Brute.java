package synth.tot;

import java.util.Random;

public class Brute {

    public static void main(final String[] args) {
        final int hashM = "Miguel".hashCode();
        final int hashN = "Nelson".hashCode();
        final int source = "empty".hashCode();
        brute(hashM, hashN, source);
    }

    private static void brute(final int m, final int n, final int base) {
        int modifier = new Random(1234).nextInt();

        int b = base;
        int x = 0;
        int y = 0;
        while (true) {
            int s = b >> (b & 0xF);
            x = (b >> s & m) & 0xF0;
            y = (b << s & n) & 0xF0;
            x = x >> 1;
            y = y >> 1;
            b++;
            if ((x == 10) && (y == 12)) {
                break;
            }
            log(x);
            log(y);
            log(b);
        }
    }

    private static void log(final int n) {
        System.out.println(n);
    }

}
