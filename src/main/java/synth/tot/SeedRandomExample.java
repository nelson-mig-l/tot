package synth.tot;

import javax.script.ScriptException;

public class SeedRandomExample {

    public static void main(final String[] args) throws ScriptException, NoSuchMethodException {
        final SeedRandom r = SeedRandomFactory.getInstance(0);
        System.out.println(r.nextInt());
        System.out.println(r.nextInt());
        System.out.println(r.nextDouble());
        System.out.println(r.quick());
    }

}
