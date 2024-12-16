package Game.Objects.GhaviKonande;

import Game.Objects.Animatable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GhaviKonande implements Animatable {
    private double x;
    private double y;
    private transient double vx;
    private transient double vy;
    public transient BufferedImage bufferedImage;
    public int type;
    private transient Random random=new Random();

    public int getType() {
        return type;
    }

    public GhaviKonande(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        int a=random.nextInt(8);
        type=4;
        if(a<6){
            type=3;
        }
        if(a==0){
            type=0;
        }
        if(a==1){
            type=1;
        }
        if(a==2){
            type=2;
        }

      //  System.out.println(type);
        try {
            if(type==0){
            bufferedImage = ImageIO.read(new File("pngs/ghaviKonande1.png"));}
            if(type==1){
                bufferedImage = ImageIO.read(new File("pngs/ghaviKonande2.png"));}
            if(type==2){
                bufferedImage = ImageIO.read(new File("pngs/ghaviKonande3.png"));}
            if(type==3){
                bufferedImage = ImageIO.read(new File("pngs/ghaviTedad.png"));
            }
            if (type==4){
                bufferedImage = ImageIO.read(new File("pngs/ghaviMaxTeta.png"));
            }
           // tx.rotate(Math.atan2(vy, vx), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
            AffineTransform tx = new AffineTransform();

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
    public void load(double x, double y , double vx,double vy,int type){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.type=type;
    }
}

