package Reflection;

import Game.Objects.Chicken.Chicken;

import java.util.ArrayList;

public class CharkheshiMove {
    public CharkheshiMove() {

    }
    public void chickenMove(ArrayList<Chicken> chickens){
        System.out.println("op");
        for(Chicken chicken:chickens){
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
    }
}
