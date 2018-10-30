package problems.farmerwolfgoat;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import framework.AbstractState;
import framework.State;

public class FarmerWolfGoatState extends AbstractState {

    enum Side {
        EAST {
            @Override
            Side getOpposite() {
                return WEST;
            }
        }, WEST {
            @Override
            Side getOpposite() {
                return EAST;
            }
        };

        abstract Side getOpposite();

    }
    private Side farmer = Side.EAST;

    private Side wolf = Side.EAST;
    private Side goat = Side.EAST;
    private Side cabbage = Side.EAST;
    public FarmerWolfGoatState() {
        super();
    }

    public FarmerWolfGoatState(final FarmerWolfGoatState parent, final Side farmer, final Side wolf, final Side goat, final Side cabbage) {
        super(parent);
        this.farmer = farmer;
        this.wolf = wolf;
        this.goat = goat;
        this.cabbage = cabbage;
    }

    @Override
    public Iterable<State> getPossibleMoves() {
        final Set<State> moves = new HashSet<>();
        if (farmer == wolf) {
            new FarmerWolfGoatState(this, farmer.getOpposite(), wolf.getOpposite(), goat, cabbage).addIfSafe(moves);
        }
        if (farmer == goat) {
            new FarmerWolfGoatState(this, farmer.getOpposite(), wolf, goat.getOpposite(), cabbage).addIfSafe(moves);
        }
        if (farmer == cabbage) {
            new FarmerWolfGoatState(this, farmer.getOpposite(), wolf, goat, cabbage.getOpposite()).addIfSafe(moves);
        }
        new FarmerWolfGoatState(this, farmer.getOpposite(), wolf, goat, cabbage).addIfSafe(moves);
        return moves;
    }

    @Override
    public boolean isSolution() {
        return farmer == Side.WEST && wolf == Side.WEST && goat == Side.WEST && cabbage == Side.WEST;
    }

    @Override
    public double getHeuristic() {
        return 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (o == null || !(o instanceof FarmerWolfGoatState)) return false;
        final FarmerWolfGoatState that = (FarmerWolfGoatState)o;
        return farmer == that.farmer && wolf == that.wolf && goat == that.goat && cabbage == that.cabbage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(farmer, wolf, goat, cabbage);
    }

    @Override
    public String toString() {
        return formatSide("Farmer", farmer) + " "
            + formatSide("Wolf", wolf) + " "
            + formatSide("Goat", goat) + " "
            + formatSide("Cabbage", cabbage);
    }

    private void addIfSafe(final Set<State> moves) {
        final boolean unsafe = ((farmer != wolf) && (farmer != goat)) || ((farmer != goat) && (farmer != cabbage));
        if (!unsafe) moves.add(this);
    }

    private String formatSide(final String what, final Side side) {
        return side == Side.EAST ? "<<" + what + "<<" : ">>" + what + ">>";
    }
}
