package Save;

import Game.Game;
import Game.Objects.Chicken.Chicken;
import Game.Objects.Coin;
import Game.Objects.Egg;
import Game.Objects.GhaviKonande.GhaviKonande;
import Game.Objects.Rocket;
import SecondMenu.SecondMenu;

import java.awt.*;
import java.sql.*;

public class Load {
    Game game;
    double x;
    double u;


    public  Load(Game game) {
        String dbmsHost = "jdbc:mysql://localhost:3306/chicken_invader";
        String user     = "root";
        String password = "";
        this.game=game;
        try {
            Connection connection = getConnection(dbmsHost, user, password);
            //ResultSet airplanePowerUpSet = selectQuery(connection , "airplanepowerups");
            Statement statement = connection.createStatement();
            ResultSet bombs=statement.executeQuery("select * from bomb where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet chickens = statement.executeQuery("select * from chickens where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet coins = statement.executeQuery("select * from coin where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet eggs = statement.executeQuery("select * from egg where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet ghavikonandes=statement.executeQuery("select * from ghavikonande where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet movingchiken=statement.executeQuery("select * from movingchicken where player = '" + SecondMenu.user + "'");
            statement = connection.createStatement();

            ResultSet rocket=statement.executeQuery("select * from rocket where player = '" + SecondMenu.user + "'");

            //mainPanel.gamePlay.getChickens().clear();
       // int c=0;
       // System.out.println(c);
            while (chickens.next()){
           //     System.out.println(c++);
                Chicken chicken=new Chicken(0,0,0,0,0,0,0,"null",1,game);
                chicken.load((double)chickens.getObject(2),(String) chickens.getObject(3)
                        ,(boolean)chickens.getObject(4), (double)chickens.getObject(5)
                        ,(double)chickens.getObject(6), (double)chickens.getObject(7),
                        (double)chickens.getObject(8),(double)chickens.getObject(9),
                        (double)chickens.getObject(10),(double)chickens.getObject(11),
                        (double)chickens.getObject(12),(double)chickens.getObject(13),
                        (int)chickens.getObject(14),(boolean)chickens.getObject(15),
                        (double)chickens.getObject(16), (double)chickens.getObject(17));
                game.chickens.add(chicken);
                switch (chicken.type){
                    case "entehari":
                        game.movingChickens.loadEntehari(chicken);
                        break;
                    case  "davarani"   :
                        game.movingChickens.loadDavarani(chicken);
                        break;
                    case "dayerei":
                        game.movingChickens.loadDayerei(chicken);
                        break;
                    case "mostatili":
                        game.movingChickens.loadMostatili(chicken);
                        break;


                }


            }
            System.out.println("my size os"+game.movingChickens.chickenDavarani.size());
            while (coins.next()){
                Coin coin=new Coin(0,0,0,0);
                coin.load((double)coins.getObject(2), (double)coins.getObject(3),
                        (double)coins.getObject(4),(double)coins.getObject(5));
                game.coins.add(coin);


            }
            while (eggs.next()){
                Egg egg=new Egg(0,0,0,0);
                egg.load((double)eggs.getObject(2), (double)eggs.getObject(3),
                        (double)eggs.getObject(4),(double)eggs.getObject(5));
                game.eggs.add(egg);

            }
            while(ghavikonandes.next()) {
                GhaviKonande ghaviKonande = new GhaviKonande(0, 0, 0, 0);
                ghaviKonande.load((double) ghavikonandes.getObject(2),
                        (double) ghavikonandes.getObject(3),
                        (double) ghavikonandes.getObject(4),
                        (double) ghavikonandes.getObject(5),
                        (int) ghavikonandes.getObject(6));
                game.ghaviKonandes.add(ghaviKonande);
            }
            while (movingchiken.next()){
                game.movingChickens.load((int) movingchiken.getObject(2),(int) movingchiken.getObject(3),(int) movingchiken.getObject(4)
                        ,(double) movingchiken.getObject(5),(double) movingchiken.getObject(6),
                        (double) movingchiken.getObject(7),(double) movingchiken.getObject(8),
                        (double) movingchiken.getObject(9),(double) movingchiken.getObject(10),
                        (double) movingchiken.getObject(11),(double) movingchiken.getObject(12),
                        (boolean) movingchiken.getObject(13));


            }


            Rocket rock=new Rocket(0,0);

            while (rocket.next()){
                 x= (double) rocket.getObject(2);
                 u= (double) rocket.getObject(3);
                rock.load((double) rocket.getObject(2), (double) rocket.getObject(3),
                        (int) rocket.getObject(4), (boolean) rocket.getObject(5)
                        ,(int) rocket.getObject(6) , (int) rocket.getObject(7) ,
                        (int) rocket.getObject(8), (int) rocket.getObject(9) ,
                        (double) rocket.getObject(10) , (double) rocket.getObject(11)
                        ,(double) rocket.getObject(12));
                game.state= (int) rocket.getObject(13);
            }
            System.out.println(x);
            System.out.println(u);

            Robot robot=new Robot();
            robot.mouseMove((int)x, (int)u );




        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(String dbmsHost, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbmsHost,user,password);
    }
}
