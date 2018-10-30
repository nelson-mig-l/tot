package synth.tot;

import java.io.InputStream;
import java.io.InputStreamReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class Prng {

    public static void main(final String[] args) throws ScriptException, NoSuchMethodException {
        final Prng prng = new Prng(0);
        System.out.println(prng.next());
        System.out.println(prng.next());
    }

    private static final String JS_SOURCE = "prng.js";

    private final ScriptObjectMirror instance;

    public Prng(final int seed) {
        final ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine engine = factory.getEngineByName("JavaScript");
        final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(JS_SOURCE);
        final InputStreamReader reader = new InputStreamReader(stream);
        try {
            engine.eval(reader);
            final Object eval = engine.eval("new Random(" + seed + ");");
            instance = (ScriptObjectMirror)eval;
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    public int next() {
        final double next = (double)instance.callMember("next");
        return (int)next;
    }

}
