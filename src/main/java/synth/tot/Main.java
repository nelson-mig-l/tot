package synth.tot;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

    public static void main(final String[] args) throws ScriptException, NoSuchMethodException {
        final int empty = "".hashCode();
        final int source = "n".hashCode();
        final int target = "m".hashCode();

        final int value = 666;
        final int init = op(value, empty);
        final int from = op(value, source);
        final int to = op(value, target);
        log(init);
        log(from);
        log(to);

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        String script = "function hello(name) {print ('Hello, ' + name); return 'bye'; }";
        //FileReader reader = new FileReader("yourFile.js");
        //engine.eval(reader);
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        final Object result = inv.invokeFunction("hello", "Nelson!!");
        System.out.println(result);
    }

    private static String hex(final int n) {
        return Integer.toHexString(n);
    }

    private static int op(final int value, final int hash) {
        return value & 0xF & hash;
    }

    private static void log(final int n) {
        System.out.println(hex(n));
    }

}
