package Game.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Egg implements Animatable{
    private double x;
    private double y;
    private transient double vx;
    private transient double vy;
    public transient BufferedImage bufferedImage;

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public Egg(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        try {

            // copied from http://www.java2s.com/Code/Java/Advanced-Graphics/RotatingaBufferedImage.htm
            bufferedImage = ImageIO.read(new File("pngs/egg.png"));

            AffineTransform tx = new AffineTransform();
           // tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            bufferedImage = op.filter(bufferedImage, null);
        } catch (IOException ex) {

        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void paint(Graphics2D g2) {
//        g2.setColor(new Color(113, 4, 5));
//        g2.setStroke(new BasicStroke(3));
//
//        double l = 25.0 / Math.sqrt(vx * vx + vy * vy);
//
//        g2.drawLine((int) (x - l * vx), (int) (y - l * vy), (int)x, (int)y);
          g2.drawImage(bufferedImage, (int)x, (int)y, null);
    }
    public void load(double x, double y, double vx,double vy) {
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
    }
}
