package Game.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bomb implements Animatable{
    private double x;
    private  double y;
    private transient double vx;
    private transient double vy;
    private transient boolean explode=false;
    public transient static int bombsLeft=3;
    private BufferedImage bufferedImage;
    private transient long reached;

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

    public Bomb(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        try {

            bufferedImage = ImageIO.read(new File("pngs/bomb.png"));

            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

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
        if((y>490)& (y<590)&(x<1020) & (x>900)){
            if (System.currentTimeMillis()-reached>300){
                explode=true;
            }
        }
        else {
            reached=System.currentTimeMillis();
            x += vx;
            y += vy;
        }
    }

    public void paint(Graphics2D g2) {
        g2.drawImage(bufferedImage, (int)x, (int)y, null);
    }


    public boolean isExplode() {
        return explode;
    }
}
