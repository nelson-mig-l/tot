package framework;

import java.util.Optional;

public abstract class AbstractState implements State {

    private final Optional<State> parent;
    private final double distance;

    public AbstractState() {
        this(null);
    }

    public AbstractState(final State parent) {
        this.parent = Optional.ofNullable(parent);
        this.distance = this.parent.isPresent() ? parent.getDistance() + 1 : 0;
    }

    @Override
    public Optional<State> getParent() {
        return parent;
    }

    @Override
    public double getDistance() {
        return distance;
    }
}
