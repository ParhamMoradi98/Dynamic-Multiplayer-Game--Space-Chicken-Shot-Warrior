package ServerClient;

import Game.Game;
import Game.LoopGame;
import Game.MainPanel.MainPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GameServer extends JPanel implements Runnable{
    ArrayList<ObjectOutputStream> outputStream;
    ArrayList<ObjectInputStream> inputStream;
    public GameServer(ArrayList<ObjectOutputStream> outputStream, ArrayList<ObjectInputStream> inputStream) {
        this.outputStream=outputStream;
        this.inputStream=inputStream;
        new Thread(this).start();

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(LoopGame.ferekans);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Game game=MainPanel.game;
        //    for (ObjectOutputStream outputStream1:outputStream) {
            for (int i = 0; i < outputStream.size(); i++) {
                try {
                    outputStream.get(i).writeObject(game);
                    outputStream.get(i).reset();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            int i =0;
            for(ObjectInputStream inputStream1:inputStream){
                i++;
                int x= 800;
                int y=800;
                boolean isPressed=false;
                try {
                    System.out.println("wow");
                    x = inputStream1.readInt();
                    y = inputStream1.readInt();
                    isPressed  = inputStream1.readBoolean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainPanel.game.rocketMoves(i,x,y,isPressed);
                System.out.println("x"+ x+"y"+y+"bool"+isPressed);
            }
        }
    }
}
