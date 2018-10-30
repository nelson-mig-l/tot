package framework;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractSolver implements Solver {

    private final Set<State> closed = new HashSet<>();

    @Override
    public List<State> solve(final State initialState) {
        closed.clear();
        clearOpen();
        addState(initialState);
        while (hasElements()) {
            final State s = nextState();
            if (s.isSolution()) {
                return findPath(s);
            }
            closed.add(s);
            final Iterable<State> moves = s.getPossibleMoves();
            moves.forEach(m -> {
                if (!closed.contains(m)) addState(m);
            });
        }
        return null;
    }

    private List<State> findPath(final State solution) {
        final LinkedList<State> path = new LinkedList<>();

        Optional<State> step = Optional.of(solution);
        while (step.isPresent()) {
            final State s = step.get();
            path.addFirst(s);
            step = s.getParent();
        }
        return path;
    }

    protected abstract boolean hasElements();
    protected abstract State nextState();
    protected abstract void addState(State s);
    protected abstract void clearOpen();
}
