package wc.prepare;

import java.util.HashMap;
import java.util.Map;

public class Transitions {

    final Map<String, Transition> storage;

    public Transitions() {
        this.storage = new HashMap<>(35);
    }

    public void add(final String from, final String to) {
        final Transition transition = storage.getOrDefault(from, new Transition(new Entity(from)));
        transition.add(to);
        storage.put(from, transition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String key : storage.keySet()) {
            sb.append(storage.get(key)).append("\n");
        }
        return sb.toString();
    }
}
