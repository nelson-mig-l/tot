package framework;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSolver extends AbstractSolver {

    private final Queue<State> queue = new PriorityQueue<>(1, Comparator.comparingDouble(s -> s.getDistance() + s.getHeuristic()));

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
