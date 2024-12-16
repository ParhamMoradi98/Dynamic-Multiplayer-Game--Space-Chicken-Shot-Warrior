package ServerClient;

import Game.Game;
import Game.Objects.Chicken.Chicken;
import Game.Objects.Coin;
import Game.Objects.Egg;
import Game.Objects.GhaviKonande.GhaviKonande;
import Game.Objects.Rocket;
import Game.Objects.Tir.Tir;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class ClientPanel extends JPanel {
Game game;
BufferedImage bufferedImage;
    public Rocket rocket;
    public  ArrayList<Egg> eggs = new ArrayList<>();
    public  ArrayList<Coin> coins = new ArrayList<>();
    public  ArrayList<GhaviKonande> ghaviKonandes = new ArrayList<>();
    public  ArrayList<Chicken> chickens =new ArrayList<>();
    private BufferedImage rockImage;
    private BufferedImage coinImage;
    private BufferedImage chickImage1;
    private BufferedImage chickImage2;
    private BufferedImage chickImage3;
    private BufferedImage chickImage4;
    private BufferedImage chickImageghool;
    private BufferedImage ghaviKonanadeImage1;
    private BufferedImage ghaviKonanadeImage2;
    private BufferedImage ghaviKonanadeImage3;
    private BufferedImage ghaviKonanadeImagetedad;
    private BufferedImage ghaviKonanadeImageteta;
    private BufferedImage tirImage1;
    private BufferedImage eggImage;
    public static boolean isPressed=false;
    public static int rocketX;
    public static int rocketY;


    public ClientPanel(Game game) {
        backGround();
        this.game=game;
        imagesname();

        loadImages();
        mouse();
    }
    private void mouse() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
//                game.getRocket().get(0).setX(e.getX());
//                game.getRocket().get(0).setY(e.getY());
                rocketX=e.getX();
                rocketY=e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                game.getRocket().get(0).setX(e.getX());
//                game.getRocket().get(0).setY(e.getY());
                rocketX=e.getX();
                rocketY=e.getY();
            }
        });
        addMouseListener(new MouseListener() {
            @Override

            public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
//                        game.getRocket().get(0).shelik("Clicked");

                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
//                        game.getRocket().get(0).throwBomb();

                    }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        isPressed = true;

                    }



            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    isPressed = false;


            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
    private void imagesname() {
        try {
            rockImage=ImageIO.read(new File("pngs/rocket.png"));
            coinImage=ImageIO.read(new File("pngs/coin.png"));
            chickImage1=ImageIO.read(new File("pngs/chicken.png"));
            chickImage2=ImageIO.read(new File("pngs/chicken2.png"));
            chickImage3=ImageIO.read(new File("pngs/chicken3.png"));
            chickImage4=ImageIO.read(new File("pngs/chicken4.png"));
            chickImageghool=ImageIO.read(new File("pngs/ghool.png"));
            ghaviKonanadeImage1 = ImageIO.read(new File("pngs/ghaviKonande1.png"));
            ghaviKonanadeImage2 = ImageIO.read(new File("pngs/ghaviKonande2.png"));
            ghaviKonanadeImage3 = ImageIO.read(new File("pngs/ghaviKonande3.png"));
            ghaviKonanadeImagetedad = ImageIO.read(new File("pngs/ghaviTedad.png"));
            ghaviKonanadeImageteta = ImageIO.read(new File("pngs/ghaviMaxTeta.png"));
            tirImage1=ImageIO.read(new File("pngs/tir.png"));
            eggImage=ImageIO.read(new File("pngs/egg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadImages() {
        for (Rocket rocket : game.rocket) {
            rocket.bufferedImage = rockImage;
        }
        for(Coin coin:game.coins){
            coin.bufferedImage=coinImage;
        }
        for(Chicken chicken:game.chickens) {
            if (chicken.chickenType == 1) {
                chicken.bufferedImage =chickImage1;

            }
            if (chicken.chickenType == 2) {
                chicken.bufferedImage =chickImage2;

            }
            if (chicken.chickenType == 3) {
                chicken.bufferedImage =chickImage3;

            }
            if (chicken.chickenType == 4) {
                chicken.bufferedImage = chickImage4;

            }
            if (chicken.chickenType == 250) {
                chicken.bufferedImage = chickImageghool;

            }
            for(GhaviKonande ghaviKonande:game.ghaviKonandes){
                if(ghaviKonande.type==0){
                    ghaviKonande.bufferedImage =ghaviKonanadeImage1;}
                if(ghaviKonande.type==1){
                    ghaviKonande.bufferedImage = ghaviKonanadeImage2;}
                if(ghaviKonande.type==2){
                    ghaviKonande.bufferedImage = ghaviKonanadeImage3;}
                if(ghaviKonande.type==3){
                    ghaviKonande.bufferedImage = ghaviKonanadeImagetedad;
                }
                if (ghaviKonande.type==4){
                    ghaviKonande.bufferedImage = ghaviKonanadeImageteta;
                }
            }
            for (Rocket rocket : game.rocket) {

                for (Tir tir : rocket.getTirs()) {
                    tir.bufferedImage = tirImage1;

                }
            }
        }

    }

    private void backGround() {
        try {
            bufferedImage= ImageIO.read(new File("pngs/Game.png"));
            setBounds(0, 0, 1920,1080);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void updateGame(Game game){
        this.game=game;
       // loadImages();
//        System.out.println(game.chickens.size());
        this.revalidate();
        this.repaint();

//        this.chickens=game.chickens;
//        this.eggs=game.eggs;
//        this.coins=game.coins;
//        this.ghaviKonandes=game.ghaviKonandes;
//        this.rocket=game.rocket;
   }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        loadImages();
        g.drawImage(bufferedImage, 0, 0, null);
        Graphics2D g2 = (Graphics2D) g;
     //   game.paint(g2);
        for (Egg egg : game.eggs) {
            g2.drawImage(eggImage, (int)egg.getX(), (int)egg.getY(), null);
        }

        for (Coin coin:game.coins) {
            g2.drawImage(coinImage, (int)coin.getX(), (int)coin.getY(), null);
        }
        System.out.println(game.chickens.size());
        for (Chicken chicken : game.chickens) {

        //    System.out.println("chicken x"+chicken.getX() + "chicken y"+chicken.getY());
            chicken.paint(g2);
            g2.drawImage(chickImage2, (int)chicken.getX(), (int)chicken.getY(), null);



        }
        g2.drawImage(chickImage1,0,0,null);
        for(GhaviKonande ghaviKonande:game.ghaviKonandes){
            ghaviKonande.paint(g2);

        }
        for (Rocket rocket : game.rocket) {
            rocket.paint(g2);
        }
    }
}
