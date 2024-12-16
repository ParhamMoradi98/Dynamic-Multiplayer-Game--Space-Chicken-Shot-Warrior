package Game;

import Game.MainPanel.MainPanel;
import Game.Objects.*;
import Game.Objects.Chicken.Chicken;
import Game.Objects.GhaviKonande.GhaviKonande;
import Game.Objects.Moving.MovingChickens;
import Game.Objects.Tir.Tir;
import MainClass.Main_Class;
import Save.Load;
import Save.Save;
import SecondMenu.SecondMenu;
import ServerClient.ClientPanel;
import ServerClient.ServerWaiting;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game implements Serializable {

    private static boolean bombExplode=false;
    public transient static int width;
    public transient  static int height;
    public ArrayList<Rocket> rocket= new ArrayList<>();
    public transient int state= 0;
    public  final ArrayList<Egg> eggs = new ArrayList<>();
    public  final ArrayList<Coin> coins = new ArrayList<>();
    public  final ArrayList<GhaviKonande> ghaviKonandes = new ArrayList<>();
    public transient boolean oneChickenKilled=false;
    public  final ArrayList<Chicken> chickens =new ArrayList<>();
    private transient Random random=new Random();
    public transient MovingChickens movingChickens;

    BufferedImage paiin;

    {
        try {
            paiin = ImageIO.read(new File("pngs/paiin.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Game(int width, int height, MainPanel mainPanel) {
        this.width = width;
        this.height = height;
        //rocket.add(new Rocket(width / 2 - 50, height - 200));
        if(SecondMenu.runServer) {
            System.out.println("Server");

//            System.out.println("varedshodim");
            for(int i=0;i<ServerWaiting.playersNumber;i++) {

                rocket.add(new Rocket(width / 2 - 50, height - 200));
            }

        }
        else {
            System.out.println("soloPlay");
            rocket.add(new Rocket(width / 2 - 50, height - 200));

        }
        movingChickens=new MovingChickens("mf", chickens, this);
        if(SecondMenu.loaded){
            chickens.clear();
            Load load=new Load(this);
        }

        runThread(mainPanel);
    }

    private void runThread(MainPanel mainPanel) {
        new LoopGame(mainPanel,false).start();


    }

    public void paint(Graphics2D g2) {

        for (GhaviKonande ghaviKonande:ghaviKonandes){
            ghaviKonande.paint(g2);
        }


        for (Egg egg : eggs) {
            egg.paint(g2);
        }

        for (Coin coin:coins) {
            coin.paint(g2);
        }
        synchronized (chickens) {
            for (Chicken chicken1 : chickens) {
                chicken1.paint(g2);

            }
        }
        for (Rocket rocket:rocket) {
            rocket.paint(g2);
        }
        g2.setColor(Color.YELLOW);
        g2.drawImage(paiin,0,1000,null);

        g2.setFont(new Font(" ",Font.ITALIC,40));
        g2.drawString(Integer.toString(Bomb.bombsLeft),130,1040);
        g2.drawString(Integer.toString(rocket.get(0).getHeart()),60,1040);
        g2.drawString(Integer.toString(rocket.get(0).tirType+1),200,1040);
        g2.drawString(Integer.toString(rocket.get(0).getCoinsSaved()),270,1040);

        Rectangle rect=new Rectangle(40, 40,101,40);
        g2.setColor(Color.RED);
        g2.fillRect(40, 40,(int)rocket.get(0).temperature,40);
        g2.setColor(Color.YELLOW);
        g2.draw(rect);


    }

    private void removeCoin() {
        ArrayList<Coin>coinsRemoved =new ArrayList<>();
        synchronized (coins) {
            for (Coin coin : coins) {
                boolean flag = false;
                for (Rocket rocket : rocket) {
                    if (hit(coin, rocket, coin.bufferedImage, rocket.bufferedImage)) {
                        coinsRemoved.add(coin);
                        rocket.setCoinsSaved(rocket.getCoinsSaved() + 1);
                        flag = true;
                        System.out.println(rocket.getCoinsSaved());
                    }
                    for (Tir tir : rocket.getTirs()) {
                        if (hit(coin, tir, coin.bufferedImage, tir.bufferedImage) | !flag) {
                            coinsRemoved.add(coin);
                        }
                    }
                }
            }
            coins.removeAll(coinsRemoved);
        }

    }

    private void removePlane() {
        ArrayList<Egg>eggsRemove =new ArrayList<>();
        ArrayList<Chicken>chickenRemove=new ArrayList<>();
        for (Egg egg : eggs) {
            for (Rocket rocket : rocket) {

                if (hit(egg, rocket, egg.bufferedImage, rocket.bufferedImage)) {
                    eggsRemove.add(egg);
                    try {
                        Robot robot = new Robot();
                        robot.mouseMove(900, 900);
                        rocket.setHeart(rocket.getHeart() - 1);
//                        System.out.println("my heart  "+ rocket.getHeart());
                        if (rocket.getHeart() <= 0) {
                            //        System.out.println("yes");
                            try {
                                Save.saveRankings(state, rocket.getScore());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            SecondMenu secondMenu = new SecondMenu(SecondMenu.user);
                            Main_Class.jframe.getContentPane().removeAll();
                            Main_Class.jframe.getContentPane().add(secondMenu);
                            Main_Class.jframe.getContentPane().revalidate();
                            Main_Class.jframe.getContentPane().repaint();

                        }
                        eggsRemove.add(egg);
                        break;
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }


                }

            }
        }
        synchronized (chickens) {
            for (Chicken chicken : chickens) {
                for (Rocket rocket : rocket) {

                    if (hit(chicken, rocket, chicken.bufferedImage, rocket.bufferedImage)) {
                        try {
                            Robot robot = new Robot();
                            robot.mouseMove(900, 900);
                            rocket.setHeart(rocket.getHeart() - 1);
                            //    System.out.println("my heart  "+ rocket.getHeart());
                            if (rocket.getHeart() <= 0) {
//                        System.out.println("yes");
                                try {
                                    Save.saveRankings(state, rocket.getScore());
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                SecondMenu secondMenu = new SecondMenu(SecondMenu.user);
                                Main_Class.jframe.getContentPane().removeAll();
                                Main_Class.jframe.getContentPane().add(secondMenu);
                                Main_Class.jframe.getContentPane().revalidate();
                                Main_Class.jframe.getContentPane().repaint();

                            }
                            chickenRemove.add(chicken);
                            break;
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        }

        eggs.removeAll(eggsRemove);
        chickens.removeAll(chickenRemove);
    }

    private void saveRankings() {

    }

    private void removeGhaviKonande(){
        ArrayList<GhaviKonande>ghaviKonanderemoved =new ArrayList<>();
        for (Rocket rocket : rocket) {

            for (GhaviKonande ghaviKonande : ghaviKonandes) {

                if (hit(ghaviKonande, rocket, ghaviKonande.bufferedImage, rocket.bufferedImage)) {
                    ghaviKonanderemoved.add(ghaviKonande);
                    if (ghaviKonande.getType() == 3) {
                        if (rocket.tirPerShelik < 4) {
                            rocket.tirPerShelik++;
                        } else {
                            rocket.power = rocket.power * 1.25;
                            for (Tir tir : rocket.getTirs()) {
                                tir.power = tir.getNormalPower() * rocket.power;
                                //    System.out.println("roc Power" + rocket.power+ " norm po"+ tir.getNormalPower());
                            }
                        }
                    } else {
                        if (ghaviKonande.getType() == 4) {
                            rocket.setMaxTemperature(rocket.getTemperature() + 5);
                        } else {
                            rocket.setType(ghaviKonande.getType());
                            //  System.out.println(rocket.getType());
                        }
                    }
                }

            }
        }
        ghaviKonandes.removeAll(ghaviKonanderemoved);
    }


    public void removeChicken() {
        ArrayList<Tir> tirRemove = new ArrayList<>();
        for (Rocket rocket1 : rocket) {

            synchronized (chickens) {
                for (Chicken chicken1 : chickens) {
                    synchronized (rocket1.getTirs()) {

                        for(Iterator<Tir> iterator = rocket1.getTirs().iterator(); iterator.hasNext();){
                            Tir tir=iterator.next();
                            if (hit(tir, chicken1, tir.bufferedImage, chicken1.bufferedImage)) {
                                chicken1.setHealth(chicken1.getHealth() - tir.power);
                                rocket1.setScore(rocket1.getScore() + chicken1.chickenType);
                                if (chicken1.getHealth() < 1) {
                                    chicken1.setKilled(true);
                                    oneChickenKilled = true;

                                    if (random.nextInt(100) < 6) {
                                        coins.add(new Coin(chicken1.getX(), chicken1.getY(), 0, 5));
                                    } else {
                                        if (random.nextInt(100) < 6) {
                                            ghaviKonandes.add(new GhaviKonande(chicken1.getX(), chicken1.getY(), 0, 5));
                                        }


                                    }
                                }

                                tirRemove.add(tir);
                            }

                        }
                    }
                }

            }
            ArrayList<Tir> tirs=rocket1.getTirs();
            tirs.removeAll(tirRemove);
            rocket1.setTirs(tirs);
        }

    }
    public void move() {
        if(bombExplode){
            chickens.removeAll(chickens);
            eggs.removeAll(eggs);
            bombExplode=false;
        }
        if(oneChickenKilled){
            ArrayList<Chicken>chickensRemove =new ArrayList<>();
            synchronized (chickens) {
                for (Chicken chicken : chickens) {
                    if (chicken.isKilled()) {
                        chickensRemove.add(chicken);
                    }
                }
            }
            synchronized (chickens) {
                chickens.removeAll(chickensRemove);
            }
        }
        if(chickens.size()==0){
            state++;
            for (Rocket rocket : rocket) {

                if (state % 4 == 0) {
                    rocket.setScore(rocket.getScore() + 3 * rocket.getCoinsSaved());
                    rocket.setCoinsSaved(0);
                }
            }
            movingChickens.state(state);
        }
        movingChickens.updateCenter();
        synchronized (chickens) {

            for (Chicken chicken1 : chickens) {
                chicken1.move();
            }
            //
            //          .println(chicken1.teta);

        }
        for (GhaviKonande ghaviKonande:ghaviKonandes){
            ghaviKonande.move();
        }
        ArrayList<Egg> removeEgg=new ArrayList<>();
        synchronized (eggs) {
            for (Egg egg : eggs) {

                egg.move();
                if (egg.getY() > 1080) {
                    removeEgg.add(egg);
                }

            }

            eggs.removeAll(removeEgg);
        }

        for (Rocket rocket : rocket) {

            rocket.move();
        }
        for (Coin coin:coins) {
            coin.move();
        }
        removeChicken();
        removePlane();
        removeCoin();
        removeGhaviKonande();

    }
    public ArrayList<Rocket> getRocket() {
        return rocket;
    }




    private boolean hit(Animatable a , Animatable b,BufferedImage bf1 , BufferedImage bf2){
        return ((a.getX()+ bf1.getHeight()< b.getX()+ bf2.getHeight() & a.getX()+ bf1.getHeight()> b.getX()
                |  a.getX()+ bf1.getHeight()> b.getX()+ bf2.getHeight() & a.getX()< b.getX()+ bf2.getHeight()
        ) &
                ((a.getY()+ bf1.getWidth()< b.getY()+ bf2.getWidth() & a.getY()+ bf1.getWidth()> b.getY()
                        |  a.getY()+ bf1.getWidth()> b.getY()+ bf2.getWidth() & a.getY()< b.getY()+ bf2.getWidth()
                )));

    }

    public static void setBombExplode(boolean bombExplode) {
        Game.bombExplode = bombExplode;
    }
    public  void rocketMoves(int index,int x,int y, boolean isPressed){
        rocket.get(index).setX(x);
        rocket.get(index).setY(y);
        if(isPressed){
            rocket.get(index).shelik("");
        }
    }
}

