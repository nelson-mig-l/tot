package framework;

import java.util.Stack;

public class DepthFirstSolver extends AbstractSolver {

    private final Stack<State> stack = new Stack<>();

    @Override
    protected boolean hasElements() {
        return !stack.isEmpty();
    }

    @Override
    protected State nextState() {
        return stack.pop();
    }

    @Override
    protected void addState(final State s) {
        if (!stack.contains(s)) stack.push(s);
    }

    @Override
    protected void clearOpen() {
        stack.clear();
    }
}
