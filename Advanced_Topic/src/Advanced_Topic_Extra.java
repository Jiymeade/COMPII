import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Advanced_Topic_Extra extends JFrame {
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        int width = getWidth();
        int height = getHeight();
        GradientPaint sky = new GradientPaint(
                0, 0, new Color(10, 10, 35), 0, height, new Color(40, 0, 70));
        graphics2D.setPaint(sky);
        graphics2D.fillRect(0, 0, width, height);

        graphics2D.setColor(Color.WHITE);
        for (int i = 0; i < 150; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int size = (int) (Math.random() * 3) + 2;
            graphics2D.fill(new Ellipse2D.Double(x, y, size, size));
        }

        graphics2D.setColor(new Color(250, 250, 210));
        graphics2D.fill(new Ellipse2D.Double(width - 180, 60, 120, 120));

        graphics2D.setColor(new Color(20, 20, 40));
        int building1X = 80, building1Y = height - 400, buiding1W = 180, building1H = 400;
        graphics2D.fill(new Rectangle2D.Double(building1X, building1Y,buiding1W, building1H));

        graphics2D.setColor(new Color(255, 230, 90));
        for(int y = building1Y + 20; y < height - 60; y += 40){
            for(int x = building1X + 20; x < building1X + buiding1W - 25; x += 40){
                graphics2D.fillRect(x, y, 20, 25);
            }
        }
        graphics2D.setColor(new Color(150,100,60));
        int doorWidth = 50;
        int doorHeight = 60;
        int doorX = building1X + (buiding1W / 2) - (doorWidth / 2);
        int doorY = height - doorHeight;
        graphics2D.fill(new Rectangle2D.Double(doorX, doorY, doorWidth, doorHeight));

        graphics2D.setColor(new Color(30, 30, 60));
        int building2X = 300, building2Y = height - 550, building2W = 220, building2H = 550;
        graphics2D.fill(new Rectangle2D.Double(building2X, building2Y, building2W, building2H));


        graphics2D.setColor(new Color(255, 215, 70));
        for (int y = building2Y + 20; y < height - 80; y += 45) {
            for (int x = building2X + 20; x < building2X + building2W - 25; x += 45) {
                graphics2D.fillRect(x, y, 22, 25);
            }
        }
        graphics2D.setColor(new Color(150,100,60));
        int doorsWidth = 50;
        int doorsHeight = 70;
        int doorsX = building2X + (building2W / 2) - (doorWidth / 2);
        int doorsY = height - doorHeight;
        graphics2D.fill(new Rectangle2D.Double(doorsX, doorsY, doorsWidth, doorsHeight));

        int ufoWidth = 160;
        int ufoHeight = 40;
        int ufoX = building2X + (building2W / 2) - (ufoWidth / 2);
        int ufoY = building2Y - 120;

        graphics2D.setColor(new Color(180, 180, 200));
        graphics2D.fill(new Ellipse2D.Double(ufoX, ufoY, ufoWidth, ufoHeight));

        graphics2D.setColor(new Color(200, 255, 255, 180));
        graphics2D.fill(new Ellipse2D.Double(ufoX + 40, ufoY - 25, 80, 40));

        graphics2D.setColor(new Color(255, 255, 100, 120));
        Polygon beam = new Polygon();
        beam.addPoint(ufoX + ufoWidth / 2, ufoY + ufoHeight);
        beam.addPoint(building2X, height);
        beam.addPoint(building2X + building2W, height);
        graphics2D.fillPolygon(beam);

    }
    public static void main(String[] args) {
        Advanced_Topic_Extra frame = new Advanced_Topic_Extra();
        frame.setVisible(true);
    }
}


