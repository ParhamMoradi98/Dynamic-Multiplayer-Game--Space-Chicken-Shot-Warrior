package Game.Objects;

import Game.Game;
import Game.Objects.Tir.Tir;
import Game.Objects.Tir.Tir2;
import Game.Objects.Tir.Tir3;
import SecondMenu.SecondMenu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.applet.Applet;
import java.applet.AudioClip;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Rocket implements Animatable {
    public transient int tirPerShelik=1;
    public transient boolean daghKardim;
    private double x;
    private double y;
    private transient double score;
    transient File  shootfile;
    transient  File missilefile;
    transient  File chickenfile;
    transient  File explodefile;
    transient  URL shooturl = null;
    transient  URL missileurl = null;
    transient  URL chickenurl = null;
    transient  URL explodeurl = null;
    transient   AudioClip shootclip;
    transient  AudioClip missileclip;
    transient  AudioClip chickenclip;
    transient  AudioClip explodeclip;
    transient int c=0;
    private transient long time;
    private transient int coinsSaved=0;
    private transient int heart =3;
    public transient int tirType=0;
    public transient BufferedImage bufferedImage;
    public transient  double temperature=0;
    private transient double maxTemperature=100;
    private  ArrayList<Tir> tirs = new ArrayList<>();
    public transient long prev=System.currentTimeMillis();
    private static ArrayList<Bomb> bombs = new ArrayList<>();
    public transient double power=1;

    public Rocket(int x, int y) {
        shootfile = new File("shoot.wav");
        if (shootfile.canRead()) {try {
            shooturl = shootfile.toURI().toURL();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
        shootclip = Applet.newAudioClip(shooturl);
        this.x = x;
        this.y = y;

        try {
            if(!SecondMenu.plane){
                bufferedImage = ImageIO.read(new File("pngs/rocket.png"));

            }
            else {
                bufferedImage = ImageIO.read(new File("pngs/rocket2.png"));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void paint(Graphics2D g2) {
        for (Bomb bomb : bombs) {
            bomb.paint(g2);
        }
        synchronized (tirs) {
            for (Tir tir : tirs) {
                tir.paint(g2);
            }
        }
        if (bombs.size() >= 1){
            if (bombs.get(bombs.size() - 1).isExplode()){
                bombs.remove(bombs.size() - 1);
                this.setTirs(new ArrayList<Tir>());
                Game.setBombExplode(true);
            }
        }
        g2.drawImage(bufferedImage, (int)x - bufferedImage.getWidth()/2,(int) y - bufferedImage.getHeight()/2, null);
        if(temperature>maxTemperature){
            daghKardim=true;
            time=System.currentTimeMillis();
        }
        if(System.currentTimeMillis()-time>4000 & daghKardim){
            daghKardim=false;

        }
        if(temperature>0){
            if(temperature>2)
                temperature= temperature-2;
            else
                temperature=0;
        }
        g2.setColor(Color.YELLOW);
        g2.drawString(Integer.toString(heart),150,40);


    }

    public void move() {
        ArrayList<Tir> removeTir= new ArrayList<>();
        synchronized (tirs) {
            for (Tir tir : tirs) {
                if(tir.getY()<0){
                    removeTir.add(tir);
                }
                tir.move();


            }
            tirs.removeAll(removeTir);
        }
        for (Bomb bomb : bombs) {
            bomb.move();
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void increaseTemp(int teta) {
        temperature=temperature+teta+5;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {

        this.heart = heart;
    }

    public int getScore() {
        return (int)score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoinsSaved() {
        return coinsSaved;
    }

    public void setCoinsSaved(int coinsSaved) {
        this.coinsSaved = coinsSaved;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getType() {
        return tirType;
    }
    public void shelik(String s) {
      //  System.out.println("tir typr" + tirPerShelik);

        if (System.currentTimeMillis() - prev > (tirType+2)*50 & this.getTemperature()<100 & !this.daghKardim) {
      //      System.out.println("tir type"+ tirType+" "+c++);
        int r=50;
            synchronized (tirs) {
                if (tirPerShelik == 1) {
                    switch (tirType) {
                        case 0:
                   //         System.out.println("increased tete = "+Tir.getIncreasetTeta() );
                            tirs.add(new Tir(this.getX(), this.getY(), 0, -10));
                            this.increaseTemp(Tir.getIncreasetTeta());
                            break;
                        case 1:
                            tirs.add(new Tir2(this.getX(), this.getY(), 0, -15));
                            this.increaseTemp(Tir2.getIncreasetTeta());
                            break;
                        case 2:
                            tirs.add(new Tir3(this.getX(), this.getY(), 0, -20));
                            this.increaseTemp(Tir3.getIncreasetTeta());
                            break;
                    }

                }

                if (tirPerShelik == 2) {
                    switch (tirType) {
                        case 0:

                            tirs.add(new Tir(this.getX() - 20, this.getY(), 0, -10));
                            tirs.add(new Tir(this.getX() + 20, this.getY(), 0, -10));

                            this.increaseTemp(Tir.getIncreasetTeta());
                            break;
                        case 1:
                            tirs.add(new Tir2(this.getX() - 20, this.getY(), 0, -15));
                            tirs.add(new Tir2(this.getX() + 20, this.getY(), 0, -15));
                            this.increaseTemp(Tir2.getIncreasetTeta());
                            break;
                        case 2:
                            tirs.add(new Tir3(this.getX() - 20, this.getY(), 0, -20));
                            tirs.add(new Tir3(this.getX() + 20, this.getY(), 0, -20));

                            this.increaseTemp(Tir3.getIncreasetTeta());
                            break;
                    }
                }
                if (tirPerShelik == 3) {
                    switch (tirType) {
                        case 0:

                            double degree = (70+1 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+2 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+3 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            this.increaseTemp(Tir.getIncreasetTeta());
                            break;
                        case 1:
                            degree = (70+1 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+2 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+3 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            this.increaseTemp(Tir2.getIncreasetTeta());
                            break;
                        case 2:
                            degree = (70+1 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+2 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (70+3 * 10) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            this.increaseTemp(Tir3.getIncreasetTeta());
                            break;
                    }
                }
                if (tirPerShelik == 4) {
                    switch (tirType) {
                        case 0:

                            double degree = (60+1 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX()+20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX()-20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+3 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));

                            this.increaseTemp(Tir.getIncreasetTeta());
                            break;
                        case 1:
                            degree = (60+1 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX()+20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() -20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+3 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir2(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));

                            this.increaseTemp(Tir2.getIncreasetTeta());
                            break;
                        case 2:
                            degree = (60+1 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX()+20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+2 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX()-20 + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));
                            degree = (60+3 * 15) / 180.0 * Math.PI;
                            tirs.add(new Tir3(this.getX() + r * Math.cos(degree),
                                    this.getY() + -r * Math.sin(degree),
                                    10 * Math.cos(degree),
                                    -10 * Math.sin(degree)));

                            this.increaseTemp(Tir3.getIncreasetTeta());
                            break;
                    }
                }
            }
            prev = System.currentTimeMillis();

            shootclip.play();

        }


    }
    public void throwBomb() {
        int r = 50;
        synchronized (bombs) {
            if (Bomb.bombsLeft > 0) {
                double degree = Math.atan2(this.getY() - 540, this.getX() - 960);
                bombs.add(new Bomb(this.getX() + r * Math.cos(degree),
                        this.getY() + -r * Math.sin(degree),
                        -10 * Math.cos(degree),
                        -10 * Math.sin(degree)));
                Bomb.bombsLeft = Bomb.bombsLeft - 1;
            }
        }
    }
    public void setType(int type) {
        this.tirType = type;
    }

    public ArrayList<Tir> getTirs() {
        return tirs;
    }

    public static ArrayList<Bomb> getBombs() {
        return bombs;
    }
    public void setTirs(ArrayList<Tir> tirs){
        this.tirs=tirs;
    }
    public void setBombs(ArrayList<Bomb> bombs){
        this.bombs=bombs;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void load(double x , double y , int tirPerShelik, boolean daghKardim,double score, int coinsSaved,int heart,
                     int tirType, double temperature, double maxTemperature,double power) {
        this.x=x;
        this.y=y;
        this.tirPerShelik=tirPerShelik;
        this.daghKardim=daghKardim;
        this.score=score;
        this.coinsSaved=coinsSaved;
        this.heart=heart;
        this.tirType=tirType;
        this.temperature=temperature;
        this.maxTemperature=maxTemperature;
        this.power=power;

    }
}
