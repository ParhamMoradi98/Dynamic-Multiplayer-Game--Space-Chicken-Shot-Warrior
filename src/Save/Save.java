package Save;

import Game.Game;
import Game.Objects.Bomb;
import Game.Objects.Chicken.Chicken;
import Game.Objects.Coin;
import Game.Objects.Egg;
import Game.Objects.GhaviKonande.GhaviKonande;
import Game.Objects.Rocket;
import Game.Objects.Tir.Tir;
import SecondMenu.SecondMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Save {
    ArrayList<String> rows=new ArrayList<>();
    Game game;

    public Save(Game game) throws SQLException, ClassNotFoundException {
        this.game=game;
        String dbmsHost = "jdbc:mysql://localhost:3306/chicken_invader";
        String user     = "root";
        String password = "";
        Connection connection = getConnection(dbmsHost, user, password);
        rows.add("bomb");
        rows.add("chickens");
        rows.add("coin");
        rows.add("egg");
        rows.add("ghaviKonande");
        rows.add("movingChicken");
        rows.add("rocket");
        rows.add("tir");
        deleteQuery(connection);
        insertQuery(connection);


    }

    private static Connection getConnection(String dbmsHost, String user, String password) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(dbmsHost,user,password);
    }
    public void deleteQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        for(String row : rows){
            statement.executeUpdate("delete from "+ row + " where player = '"+ SecondMenu.user+"'");

        }
    }
    public void insertQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
//        for (Bomb bomb: Rocket.getBombs()) {
//            statement.executeUpdate("insert into bomb (player, x,y, vx, vy,bombsLeft,reached ) values ('"+ SecondMenu.user+"'"+ " ,"+
//                    Double.toString(bomb.getX())+ " ,"+bomb.getY()+ " ,"+
//                    bomb.getVx()+ " ,"+ bomb.getVy()+")");
//        }
        for (Chicken chicken: game.chickens) {
            statement.executeUpdate("insert into chickens (player,radif,typ,rocketKiller, x,y, vx, vy,health , " +
                    "prevxcenter , prevycenter ,teta, radius , layer , gotIt , randX , randY)" +
                    " values ('"+ SecondMenu.user+"' , "+chicken.radif + " ," + "'"+chicken.type+ "' ,"+chicken.rocketKiller + " ,"+
                    Double.toString(chicken.getX())+ " ,"+chicken.getY()+ " ,"+
                    chicken.getVx()+ " ,"+ chicken.getVy()+ " ," + chicken.getHealth()+ " ,"+chicken.prevxcenter+ " ,"+chicken.prevycenter+ " ,"+
                    chicken.teta+","+chicken.radius + " ,"+ chicken.layer+ " ," + chicken.gotIt + " ," + chicken.randX + " ,"+ chicken.randY + ")");
        }
        for (Coin coin: game.coins) {
            statement.executeUpdate("insert into coin (player, x,y, vx, vy ) values ('"+ SecondMenu.user+"'"+ " ,"+
                    Double.toString(coin.getX())+ " ,"+coin.getY()+ " ,"+
                    coin.getVx()+ " ,"+ coin.getVy()+")");
        }

        for (Egg egg: game.eggs) {
            synchronized (game.eggs) {
                statement.executeUpdate("insert into egg (player, x,y, vx, vy ) values ('" + SecondMenu.user + "'" + " ," +
                        Double.toString(egg.getX()) + " ," + egg.getY() + " ," +
                        egg.getVx() + " ," + egg.getVy() + ")");
            }
        }
        for(GhaviKonande ghaviKonande: game.ghaviKonandes){
            statement.executeUpdate("insert into ghavikonande (player, x,y, vx, vy,type ) values ('"+ SecondMenu.user+"'"+ " ,"+
                    Double.toString(ghaviKonande.getX())+ " ,"+ghaviKonande.getY()+ " ,"+
                    ghaviKonande.getVx()+ " ,"+ ghaviKonande.getVy()+","+ ghaviKonande.getType()+")");

        }
        statement.executeUpdate("insert into movingchicken (player, radius,xcenter, ycenter, prevxcenter,prevycenter,prevxcenterDavarani" +
                ", prevycenterDavarani , previousTime , deltax , deltay , vmostatili, rocketFolowed ) values ('"+ SecondMenu.user+"'"+ " ,"+
                game.movingChickens.radius+ " ," +game.movingChickens.xcenter+ " ,"+
                game.movingChickens.ycenter+ " ,"+ game.movingChickens.prevxcenter+ " ,"
                +game.movingChickens.prevycenter+ " ,"+ game.movingChickens.prevxcenterDavarani+ " ,"+game.movingChickens.prevycenterDavarani+ " ,"
                +game.movingChickens.previousTime+ " ,"
                +game.movingChickens.deltax+ " ,"+game.movingChickens.deltay+ " ,"
                +game.movingChickens.vmostatili+ " ,"+game.movingChickens.rocketFolowed+")");
        statement.executeUpdate("insert into rocket (player, x,y,tirPerShelik, daghKardim, score, coinsSaved , heart" +
                " , tirType , temperature , maxTemperature  , power, state ) values ('"+ SecondMenu.user+"'"+ " ,"+
                Double.toString(game.rocket.get(0).getX())+ " ,"+game.rocket.get(0).getY()+ " ,"+
                game.rocket.get(0).tirPerShelik+ " ,"+ game.rocket.get(0).daghKardim + " ,"+game.rocket.get(0).getScore()+ " ,"+
                game.rocket.get(0).getCoinsSaved()+ " ,"+ game.rocket.get(0).getHeart()+ " ,"+game.rocket.get(0).tirType+
                " ," + game.rocket.get(0).temperature+ " ," +game.rocket.get(0).getMaxTemperature()+ " ,"
                + game.rocket.get(0).power+","+game.state+")");
//        synchronized (game.getRocket().getTirs()) {
//            for (Tir tir : game.rocket.getTirs()) {
//                statement.executeUpdate("insert into tir (player, x,y, vx, vy,normalPower,increasetTeta, power ) values ('" + SecondMenu.user + "'" + " ," +
//                        Double.toString(tir.getX()) + " ," + tir.getY() + " ," +
//                        tir.getVx() + " ," + tir.getVy() + " ," + tir.normalPower + " ," + tir.increasetTeta + " ," + tir.power + ")");
//
//            }
//        }
    }
    public static void saveRankings(int state, int score) throws SQLException, ClassNotFoundException {
        String dbmsHost = "jdbc:mysql://localhost:3306/chicken_invader";
        String user     = "root";
        String password = "";
        Connection connection = getConnection(dbmsHost, user, password);


        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into rankings (player, state,score) values ('"+ SecondMenu.user+"'"+ " ,"+
                state+","+score
                +")");

    }



}
