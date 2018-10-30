package problems.farmerwolfgoat;

import java.util.List;

import framework.BestFirstSolver;
import framework.BreadthFirstSolver;
import framework.DepthFirstSolver;
import framework.Solver;
import framework.State;

public class Main {

    public static void main(final String args[]) {
        final FarmerWolfGoatState initialState = new FarmerWolfGoatState();

        final Solver depth = new DepthFirstSolver();
        printSolution(" Depth First ", depth.solve(initialState));

        final Solver breadth = new BreadthFirstSolver();
        printSolution("Breadth First", breadth.solve(initialState));

        final Solver best = new BestFirstSolver();
        printSolution(" Best  First ", best.solve(initialState));
    }

    private static void printSolution(final String what, final List<State> solution) {
        System.out.println("=== " + what + " ===");
        int index = 0;
        for (final State step : solution) {
            System.out.println(index++ + ") " + step.toString());
        }
    }

}
