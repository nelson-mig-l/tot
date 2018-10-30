package framework;

import java.util.List;

public interface Solver {

    List<State> solve(final State initialState);

}
