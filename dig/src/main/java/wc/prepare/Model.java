package wc.prepare;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.Range;

public class Model {

    private static final Random RANDOM = new Random();

    private class Prediction {
        Map<Range<Double>, String> probabilities = new HashMap<>();

        private double lower = 0.0;

        private void add(final String letter, final double frequency) {
            final double upper = lower + frequency;
            final Range<Double> range = Range.openClosed(lower, upper);
            probabilities.put(range, letter);
            lower = upper;
        }

        private String get() {
            final double value = RANDOM.nextDouble();
            for (final Range<Double> range : probabilities.keySet()) {
                if (range.contains(value)) {
                    return probabilities.get(range);
                }
            }
            return "~";
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (final Range<Double> r : probabilities.keySet()) {
                final double delta = r.upperEndpoint() - r.lowerEndpoint();
                final double pc = delta * 100.0;
                sb.append(probabilities.get(r)).append(" # ").append((int)pc+"%").append(" ").append(r).append("\n");
            }
            return sb.toString();
        }
    }

    private class Predictions {
        Map<String, Prediction> predictions = new HashMap<>();

        private void add(final String from, final Prediction prediction) {
            predictions.put(from, prediction);
        }

        String next(final String from) {
            final Prediction prediction = predictions.get(from);
            if (prediction == null) {
                return "~";
            }
            return prediction.get();
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (final String key : predictions.keySet()) {
                sb.append("=== ").append(key)
                    .append(" (" + predictions.get(key).probabilities.size() + ")\n")
                    .append(predictions.get(key)).append("\n");
            }
            return sb.toString();
        }
    }

    private Map<String, Predictions> model;
    final Predictions predictions = new Predictions();

    public Model(final Transitions transitions) {


        ////

        for (final String from : transitions.storage.keySet()) {
            final Transition transition = transitions.storage.get(from);
            final double total = transition.from.frequency;
            final Prediction prediction = new Prediction();
            for (final String to : transition.to.keySet()) {
                final double count = transition.to.get(to).frequency;
                final double frequency = count / total;
                //System.out.println(from + " -> " + transition.to.get(to) + " # " + frequency);
                prediction.add(to, frequency);
            }
            predictions.add(from, prediction);
        }
    }

    @Override
    public String toString() {
        return predictions.toString();
    }

    public String create(final int lenght, final String start) {
        final StringBuilder sb = new StringBuilder(start);
        String prev = start;
        String curr;
        for (int index = 0; index < lenght; index++) {
            curr = predictions.next(prev);
            sb.append(curr);
            prev = curr;
        }
        return sb.toString();
    }

}
