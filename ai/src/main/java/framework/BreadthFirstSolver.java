package framework;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSolver extends AbstractSolver {

    private final Queue<State> queue = new LinkedList<>();

    @Override
    protected boolean hasElements() {
        return !queue.isEmpty();
    }

    @Override
    protected State nextState() {
        return queue.remove();
    }

    @Override
    protected void addState(final State s) {
        if (!queue.contains(s)) queue.offer(s);
    }

    @Override
    protected void clearOpen() {
        queue.clear();
    }
}
