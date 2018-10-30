package wc.prepare;

public class Entity {

    final String letter;
    long frequency;

    public Entity(final String letter) {
        this.letter = letter;
        this.frequency = 0;
    }

    public void increase() {
        frequency++;
    }

    @Override
    public String toString() {
        return letter + "(" + frequency + ")";
    }
}
