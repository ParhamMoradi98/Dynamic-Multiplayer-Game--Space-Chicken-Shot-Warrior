package Game.Objects.Chicken;

import Game.Game;
import Game.Objects.Animatable;
import Game.Objects.Egg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Chicken implements Animatable {
    private transient final Game game;
    public transient double radif;
    public  String type;
    public transient boolean rocketKiller;
    private  double x;
    private double y;
    private transient double vx;
    private transient double vy;
    private transient double health=1;
    public transient BufferedImage bufferedImage;
    private transient boolean Killed;
    public transient double prevxcenter;
    public transient double prevycenter;
    public transient double teta;
    public transient double radius;
    public transient int layer;
    public transient boolean gotIt=false;
    public transient  double randX;
    public transient double randY;
    public transient int chickenType;
    public transient double vXTokhm;
    public transient double vyTokhm;
    public transient double ehtemalTokhm=0.05;
    private transient long time= System.currentTimeMillis();
    transient Random random=new Random();
    public Chicken(double x, double y, double vx, double vy , int layer, double radius, double radif, String type, int chickenType, Game game) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        Killed=false;
        this.layer=layer;
        this.radius=radius;
        this.radif=radif;
        this.type=type;
        this.chickenType=chickenType;
        this.game=game;
        try {
            if(chickenType==1) {
                bufferedImage = ImageIO.read(new File("pngs/chicken.png"));
                this.health=2;
                this.vXTokhm=0;
                this.vyTokhm=2.5;
                this.ehtemalTokhm=0.05;

            }
            if(chickenType==2) {
                bufferedImage = ImageIO.read(new File("pngs/chicken2.png"));
                this.health=3;
                this.vXTokhm=0;
                this.vyTokhm=2.5;
                this.ehtemalTokhm=0.05;
            }
            if(chickenType==3) {
                bufferedImage = ImageIO.read(new File("pngs/chicken3.png"));
                this.health=5;
                this.vXTokhm=0;
                this.vyTokhm=5;
                this.ehtemalTokhm=0.1;
            }
            if(chickenType==4) {
                bufferedImage = ImageIO.read(new File("pngs/chicken4.png"));
                this.health=8;
                this.vXTokhm=0;
                this.vyTokhm=2.5;
                this.ehtemalTokhm=0.2;
            }
            if(chickenType==250){
                bufferedImage = ImageIO.read(new File("pngs/ghool.png"));
                this.health=250;
                this.vXTokhm=0;
                this.vyTokhm=2.5;
                this.ehtemalTokhm=0.2;
            }
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
        if(System.currentTimeMillis()-time>1000){
            time=System.currentTimeMillis();
            if(random.nextInt(100)<ehtemalTokhm*100){
                tokhmGozari();
            }
        }
        x += vx;
        y += vy;
    }


    public void setCenter(double prevxcenter, double prevycenter) {
        this.prevxcenter=prevxcenter;
        this.prevycenter=prevycenter;
    }

    public void paint(Graphics2D g2) {
     //   if (!Killed) {
            g2.drawImage(bufferedImage, (int)x, (int)y, null);

       // }


    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }


    public boolean isKilled() {
        return Killed;
    }

    public void setKilled(boolean killed) {
        Killed = killed;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getTeta() {
        return teta;
    }

    public void setTeta(double teta) {
        this.teta = teta;
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
    public void load(double radif,String type,boolean rocketKiller,double x, double y , double vx,double vy,double health
    ,double prevxcenter,double prevycenter,double teta,double radius,int layer , boolean gotIt, double randX, double randY){
        this.radif=radif;
        this.rocketKiller=rocketKiller;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.type=type;
        this.health=health;
        this.prevxcenter=prevxcenter;
        this.prevycenter=prevycenter;
        this.teta=teta;
        this.radius=radius;
        this.layer=layer;
        this.gotIt=gotIt;
        this.randX=randX;
        this.randY=randY;

    }
    public void tokhmGozari () {
        synchronized (game.eggs) {
            game.eggs.add(new Egg(this.getX(), this.getY(), vXTokhm, vyTokhm));
        }

    }
}
