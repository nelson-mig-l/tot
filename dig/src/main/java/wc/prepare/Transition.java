package wc.prepare;

import java.util.HashMap;
import java.util.Map;

public class Transition {

    final Entity from;
    final Map<String, Entity> to;

    public Transition(final Entity from) {
        this.from = from;
        this.to = new HashMap<>(35);
    }

    public void add(final String letter) {
        from.increase();
        final Entity entity = to.getOrDefault(letter, new Entity(letter));
        entity.increase();
        to.put(letter, entity);
    }

    @Override
    public String toString() {
        final String base = from + " -> ";
        final StringBuilder sb = new StringBuilder();
        for (final String key : to.keySet()) {
            sb.append(base).append(to.get(key)).append("\n");
        }
        return sb.toString();
    }
}
