package synth.tot;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class SeedRandom {

    private final ScriptObjectMirror object;

    SeedRandom(final ScriptObjectMirror object) {
        this.object = object;
    }

    public int nextInt() {
        return call("int32");
    }

    public double nextDouble() {
        return call("double");
    }

    public double quick() {
        return call("quick");
    }

    private <T extends Number> T call(final String functionName) {
        return (T)object.callMember(functionName);
    }

}
