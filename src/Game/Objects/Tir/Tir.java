package Game.Objects.Tir;

import Game.Objects.Animatable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tir implements Animatable {
    private double x;
    private double y;
    private static double vx;
    private static double vy;
    public static BufferedImage bufferedImage;
    public transient static int normalPower =1;
    public transient static int increasetTeta=5;
    public transient static double power=1;

    public static double getDelayPerShoot() {
        return delayPerShoot;
    }

    private static double delayPerShoot=0.2;
    public static int getNormalPower() {
        return normalPower;
    }

    public static void setNormalPower(int po) {
        normalPower = po;
    }

    public Tir(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        try {

            bufferedImage = ImageIO.read(new File("pngs/tir.png"));

            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

            AffineTransformOp op = new AffineTransformOp(tx,
                    AffineTransformOp.TYPE_BILINEAR);
            bufferedImage = op.filter(bufferedImage, null);
        } catch (IOException ex) {

        }
    }
    public static int getIncreasetTeta() {
        return increasetTeta;
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
        g2.drawImage(bufferedImage, (int)x, (int)y, null);
    }

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
}
