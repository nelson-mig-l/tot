import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Creator {


    public static void main(final String[] args) throws IOException {
        final BufferedImage image = new Creator().create();
        ImageIO.write(image, "png", new File("C:\\workspaces\\ada\\tot\\ico\\src\\main\\resources\\output.png"));
    }

    private static final int FULL_SIZE = 200;
    private static final int HALF_SIZE = FULL_SIZE / 2;
    private static final int TYPE = BufferedImage.TYPE_INT_ARGB;

    private final BufferedImage image;
    private final Graphics2D g;

    public Creator() {
        image = new BufferedImage(FULL_SIZE, FULL_SIZE, TYPE);
        g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public BufferedImage create() {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawArc(0, 0, 200, 200, 0, -180);
        g.drawArc(50, 50, 100, 100, 0, -180);
        circle(100, 3, Color.RED);
        circle(50, 3, Color.RED);
        for (int i = 1; i < 4; i++) {
            circle(30*i, 1, Color.BLUE);
        }
        line(100, 10, Color.GREEN);
        line(75, 5, Color.BLACK);

        g.dispose();
        return image;
    }

    private void circle(final int radius, final int thickness, final Color color) {
        g.setColor(color);
        g.setStroke(new BasicStroke(thickness));
        final int ul = HALF_SIZE - radius;
        final int sz = radius * 2;
        g.drawArc(ul, ul, sz, sz, 0, 180);
    }

    private void line(final int lenght, final int thickness, final Color color) {
        g.setColor(color);
        g.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(HALF_SIZE, HALF_SIZE, HALF_SIZE, HALF_SIZE - lenght);
    }
}
