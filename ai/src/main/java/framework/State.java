package framework;

import java.util.Optional;

public interface State {

    Iterable<State> getPossibleMoves();

    boolean isSolution();

    double getHeuristic();

    double getDistance();

    Optional<State> getParent();

}
