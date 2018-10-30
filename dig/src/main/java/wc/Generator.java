package wc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import wc.prepare.Model;
import wc.prepare.Transitions;

public class Generator {

    public static void main(final String[] args) {
        //final Transitions transitions = new Transitions();

//        //final String input = "um dois tres quatro cinco seis sete oito nove dez nelson miguel da cruz goncalves ";
//        final String input = "oracle cos namieszalo w licencjonowaniu i mozliwosci wykorzystania javy tez produkcyjnie. "
//            + "badal moze ktos ten temat bo ja za bardzo nie kumam o co tam biega. ";
//        final int length = input.length();
//
//        String prev = input.substring(0, 1);
//        for (int index = 1; index < length; index++) {
//            String curr = input.substring(index, index + 1);
//            transitions.add(prev, curr);
//            prev = curr;
//        }

        //System.out.println(transitions);

        final Generator gen = new Generator();
        final List<String> lines = load("./dig/src/main/resources/amor-de-perdicao.txt");
        for (final String line : lines) {
            gen.read(line);
        }

        final Model model = gen.getModel();
        System.out.println(model);
        String base = "A";
        for (int i =0; i<100; i++) {
            final String created = model.create(120, base);
            System.out.println(created);
            base = created.substring(created.length() - 2, created.length() - 1).toUpperCase();
        }
    }

    private final Transitions transitions = new Transitions();

    public Generator() {

    }

    public void read(final String input) {
        final int length = input.length();

        String prev = input.substring(0, 1);
        for (int index = 1; index < length; index++) {
            String curr = input.substring(index, index + 1);
            transitions.add(prev, curr);
            prev = curr;
        }
    }

    public Model getModel() {
        return new Model(transitions);
    }



    private static List<String> load(final String file) {
        try {
            return Files.readAllLines(Paths.get(file)).stream().filter(l -> !l.isEmpty()).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
