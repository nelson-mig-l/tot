package synth.tot;

import java.io.InputStream;
import java.io.InputStreamReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class SeedRandomFactory {

    private static final String JS_SOURCE = "seedrandom.js";

    private final ScriptEngine engine;

    public static SeedRandom getInstance(final int seed) {
        return new SeedRandomFactory().create(seed);
    }

    public SeedRandomFactory() {
        final ScriptEngineManager factory = new ScriptEngineManager();
        final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(JS_SOURCE);
        final InputStreamReader reader = new InputStreamReader(stream);
        engine = factory.getEngineByName("JavaScript");
        try {
            engine.eval(reader);
        } catch (final ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    public SeedRandom create(final int seed) {
        try {
            final String script = "new Math.seedrandom(" + seed + ")";
            final ScriptObjectMirror object = (ScriptObjectMirror)engine.eval(script);
            return new SeedRandom(object);
        } catch (final ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
