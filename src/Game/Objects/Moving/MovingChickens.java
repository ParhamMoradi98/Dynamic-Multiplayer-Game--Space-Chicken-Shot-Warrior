package Game.Objects.Moving;

import Game.Game;
import Game.Objects.Chicken.Chicken;

import java.util.ArrayList;
import java.util.Random;
import Game.LoopGame;
import SecondMenu.SecondMenu;

import static java.lang.Math.*;

public class MovingChickens {
    String harekat;
    ArrayList<Chicken> chickensCharkheshi=new ArrayList<>();
    ArrayList<Chicken> chickens;
    ArrayList<Chicken> chickensDayerei =new ArrayList<>();
    ArrayList<Chicken> chickenMostatili=new ArrayList<>();
    public Chicken ghool;
    public ArrayList<Chicken> chickenDavarani=new ArrayList<>();
    ArrayList<Chicken> chickensEntehari=new ArrayList<>();
    Game game;
    private int counter=0;
    Random random=new Random();
    public  int radius=230;
    public  int xcenter=960;
    public  int ycenter=540;
    public  double prevxcenter=960;
    public  double prevycenter=540;
    public  double prevxcenterDavarani=960;
    public  double prevycenterDavarani=780;
    public  long previousTime=System.currentTimeMillis();
    public  double deltax;
    public  double deltay;
    public  double vmostatili=2;
    public  boolean rocketFolowed= false;


    public MovingChickens(String harekat, ArrayList<Chicken> chickens,Game game)  {
        this.harekat=harekat;
        this.chickens=chickens;
        this.game=game;
     //   addEntehari(10);
       // addMostatili(5);
       if( !SecondMenu.loaded){
   //        addDavarani(3, 4);
       }
    }

    private void addMostatili(int tedad_morgha) {

                for (int i = 0; i < tedad_morgha; i++) {
                    for (int j = 0; j <5; j++) {
                        Chicken chicken=new Chicken(0,j*120,5,0,0,0 , i+1, "mostatili",1,game);
                        chickens.add(chicken);
                        chickenMostatili.add(chicken);

                    }
                }

    }
    private void addDayerei(int tedad_morgha){
        for (int i = 0; i < tedad_morgha; i++) {
            prevxcenter=0;
            prevycenter=0;
            Chicken chicken = new Chicken(xcenter + radius * cos((2 * Math.PI / tedad_morgha) * i),
                    ycenter + radius * Math.sin((2 * Math.PI / tedad_morgha) * i), 0, 0, 1,0, 0, "dayerei",1,game);
            chickens.add(chicken);
            chicken.setTeta(2 * PI * i / tedad_morgha);
            chickensDayerei.add(chicken);
        }
    }
    private void addEntehari(int tedad_morgha){
        for (int i = 0; i < tedad_morgha; i++) {
            Chicken chicken = new Chicken(1920/tedad_morgha *i, 0, 0, 0, 1,0, 0,"entehari",1,game);
            chickens.add(chicken);
            chickensEntehari.add(chicken);
            chicken.randX=chicken.getX();
            chicken.randY=chicken.getY();
        }


    }
    private void  addDavarani(int numLayers , int adad){
        for (int i = 3; i < (numLayers+3); i++) {

            int tedad_morgha=i*adad;
            System.out.println( " i " + i );
            for (int j = 0; j <tedad_morgha; j++) {

                Chicken chicken = new Chicken(prevxcenterDavarani + radius*i * cos((2 * Math.PI / tedad_morgha) * j),
                        prevycenterDavarani + radius *i  * Math.sin((2 * Math.PI / tedad_morgha) * j), 0, 0, i,1000, 0,
                        "davarani",1,game);
                chickens.add(chicken);
                System.out.println(" x "+ chicken.getX());
                System.out.println( " y "+ chicken.getY() );
                chicken.setTeta(2 * PI * j / tedad_morgha);
                chickenDavarani.add(chicken);
            }
        }

    }

    private void addCharkheshi(int tedadaMorgha){
        for (int i = 0; i < tedadaMorgha; i++) {
            Chicken chicken=new Chicken(i*125,0,5,0,0,0,0,"charkheshi",2,game);
            chickensCharkheshi.add(chicken);
            chickens.add(chicken);
        }


    }



    public void updateCenter(){
        if(System.currentTimeMillis()-previousTime>4000) {
            xcenter = random.nextInt(1120)+400;
            ycenter=random.nextInt(440)+300;
             deltax=xcenter-prevxcenter;
             deltay=ycenter-prevycenter;
             previousTime=System.currentTimeMillis();

        }
        moveTowardsCenter();
    }
    private void moveTowardsCenter(){
        double vxdayerei=0;
        double vydayerei=0;
        double vxDavarani=0;
        double vyDavarani=0;

        double deltaxDavarani= game.getRocket().get(0).getX()- prevxcenterDavarani;
        double deltayDavarani= game.getRocket().get(0).getY()- prevycenterDavarani;
        if(deltay>5 || deltax>5 ){
            deltax=xcenter-prevxcenter;
            deltay=ycenter-prevycenter;
             vxdayerei=3*deltax*Math.sqrt(1/(Math.pow(deltax,2)+Math.pow(deltay,2)));
             vydayerei=3*deltay*Math.sqrt(1/(Math.pow(deltax,2)+Math.pow(deltay,2)));
        }
        if(deltayDavarani>5 || deltaxDavarani>5 || deltayDavarani< -5 ||deltaxDavarani < -5 ){
            vxDavarani=3*deltaxDavarani*Math.sqrt(1/(Math.pow(deltaxDavarani,2)+Math.pow(deltayDavarani,2)));
            vyDavarani=3*deltayDavarani*Math.sqrt(1/(Math.pow(deltaxDavarani,2)+Math.pow(deltayDavarani,2)));
        }
        prevxcenter=prevxcenter+vxdayerei;
        prevycenter=prevycenter+vydayerei;
        prevxcenterDavarani= prevxcenterDavarani+ vxDavarani;
        prevycenterDavarani= prevycenterDavarani + vyDavarani;
        for(Chicken chicken: chickensDayerei){
            chicken.setCenter(prevxcenter,prevycenter);
            chicken.setX(prevxcenter+cos(chicken.getTeta())*radius);
            chicken.setY(prevycenter+sin(chicken.getTeta())*radius);
            chicken.setTeta(chicken.getTeta()+0.015);
        }
        for (Chicken chicken: chickenMostatili){
            if(chicken.gotIt) {
                //chicken.setX(chicken.getX() + vmostatili);
                if (chicken.getX() >= 1870) {
                    chicken.setVx(-5);
                }
                if (chicken.getX() < 5) {
                    chicken.setVx(5);
                }
            }
            else {
                //System.out.println(chicken.radif);
          //      System.out.println((1920-500) *chicken.radif / 5);
                if(chicken.getX() > ((1920-500) *chicken.radif/5)){

             //      System.out.println("true shodim");
                    chicken.gotIt=true;
                    chicken.setVx(-5);
                }
            }

        }

        for (Chicken chicken: chickenDavarani){
            chicken.setCenter(prevxcenterDavarani,prevycenterDavarani);
      //      System.out.println(chickenDavarani.size());
            chicken.setX(prevxcenterDavarani+cos(chicken.getTeta())*chicken.radius*chicken.layer/2);
            chicken.setY(prevycenterDavarani+sin(chicken.getTeta())*chicken.radius* chicken.layer/2);
            if(chicken.radius>radius){
                chicken.radius=chicken.radius-10;
            }
            chicken.setTeta(chicken.getTeta()+0.015);
        }
        for(Chicken chicken:chickensCharkheshi){
            switch (chicken.layer){
                case 0:
                    if(chicken.getX()>1800){
                        chicken.setVx(0);
                        chicken.setVy(5);
                        chicken.layer=1;
                    }
                    break;
                case 1:
                    if(chicken.getY()>900){
                        chicken.setVx(-5);
                        chicken.setVy(0);
                        chicken.layer=2;
                    }
                    break;
                case 2:
                    if(chicken.getX()<50){
                        chicken.setVx(0);
                        chicken.setVy(-5);
                        chicken.layer=3;
                    }
                    break;
                case 3:
                    if(chicken.getY()<25){
                        chicken.setVx(5);
                        chicken.setVy(0);
                        chicken.layer=0;
                    }
                    break;
            }
        }
        for (Chicken chicken:chickensEntehari) {
            double deltaxEntehari= chicken.randX-chicken.getX() ;
            double deltayEntehari=  chicken.randY-chicken.getY();
            double vxDa=3*deltaxEntehari*Math.sqrt(1/(Math.pow(deltaxEntehari,2)+Math.pow(deltayEntehari,2)));
            double vyDa=3*deltayEntehari*Math.sqrt(1/(Math.pow(deltaxEntehari,2)+Math.pow(deltayEntehari,2)));

            if( !(deltaxEntehari>10 |deltaxEntehari <-10 | deltayEntehari>10 | deltayEntehari<-10 )){
                chicken.gotIt=true;
                if(chicken.rocketKiller){
                    chicken.rocketKiller=false;
                    rocketFolowed=false;
                    counter=0;
                }
            }
            if(chicken.gotIt){
               chicken.randX=random.nextInt(1520) + 200;
               chicken.randY=random.nextInt(780)+150;
               chicken.gotIt=false;
            }
            else{
                if(chicken.rocketKiller){
                    chicken.setX(chicken.getX()+2*vxDa);
                    chicken.setY(chicken.getY()+ 2*vyDa);
                }
                else {
                    chicken.setX(chicken.getX()+vxDa);
                    chicken.setY(chicken.getY()+vyDa);
                }
            }
            counter++;
            if(counter> 1000/(LoopGame.ferekans)*chickensEntehari.size()*10 &  !rocketFolowed){
                chicken.randX=game.getRocket().get(0).getX();
                chicken.randY=game.getRocket().get(0).getY();
                chicken.rocketKiller=true;
                rocketFolowed=true;
                counter=0;
            }
        }
    }

    public void load(int radius, int xcenter,int ycenter,double prevxcenter,double prevycenter,double prevxcenterDavarani,double prevycenterDavarani
    ,double previousTime,double deltax,double deltay,double vmostatili,boolean rocketFolowed) {
            this.radius=radius;
            this.xcenter=xcenter;
            this.ycenter=ycenter;
            this.prevxcenter=prevxcenter;
            this.prevycenter=prevycenter;
            this.prevxcenterDavarani=prevxcenterDavarani;
            this.prevycenterDavarani=prevycenterDavarani;
            this.previousTime= (long) previousTime;
            this.deltax=deltax;
            this.deltay=deltay;
            this.vmostatili=vmostatili;
            this.rocketFolowed=rocketFolowed;
    }
    public void loadDavarani(Chicken chicken){
        chickenDavarani.add(chicken);
    }
    public void loadMostatili(Chicken chicken){
        chickenMostatili.add(chicken);
    }
    public void loadDayerei(Chicken chicken){
        chickensDayerei.add(chicken);
    }
    public void loadEntehari(Chicken chicken){
        chickensEntehari.add(chicken);
    }

    public void state(int state) {
        switch (state){
            case 1:
                addCharkheshi(10);
                break;
            case 2:
                addDayerei(10);


                break;
            case 3:
                addDavarani(3,6);
                break;
            case 4:
                addghool(250);
                break;
            case 5:
                addCharkheshi(10);

                break;
            case 6:
                addDayerei(10);

                break;
            case 7:
                addEntehari(10);
                break;
            case 8:
                addghool(500);
                break;
            case 9:
                addMostatili(6);
                break;
            case 10:
                addCharkheshi(10);
                break;
            case 11:
                addDayerei(10);
                break;
            case 12:
                addghool(700);
                break;



        }
    }

    private void addghool(int i) {
        ghool=new Chicken(0,0,5,0,0,0,0,"mostatili",i*250/2,game);
        chickenMostatili.add(ghool);
        game.chickens.add(ghool);
    }
}
