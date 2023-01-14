package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Light {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Light(GamePanel gp, int circleSize) {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        //create screen size rectangle to make it dark
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        //get center of light circle
        int centerX = gp.screenWidth / 2;
        int centerY = gp.screenHeight / 2;

        //get top left & top right of light circle
        double x = centerX - circleSize / 2;
        double y = centerY - circleSize / 2;

        //create light circle shape
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        //create light circle area;
        Area lightArea = new Area(circleShape);

        //subtract circle area from screen area
        screenArea.subtract(lightArea);

        //create colour gradient
        Color color[] = new Color[5];
        float fraction[] = new float[5];
        color[0] = new Color(0, 0, 0, 0f);
        color[1] = new Color(0, 0, 0, 0.25f);
        color[2] = new Color(0, 0, 0, 0.50f);
        color[3] = new Color(0, 0, 0, 0.75f);
        color[4] = new Color(0, 0, 0, 0.9f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, (circleSize / 2), fraction, color);

        //set gradient on g2
        g2.setPaint(gradientPaint);

        g2.fill(lightArea);

        //draw
        g2.fill(screenArea);
        g2.dispose();
    }

    public void draw(Graphics2D g2){
            g2.drawImage(darknessFilter, 0, 0, null);
    }
}
