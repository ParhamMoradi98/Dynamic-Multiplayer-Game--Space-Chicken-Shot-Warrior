package ServerClient;

import Game.LoopGame;
import Game.MainPanel.MainPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerService implements Runnable {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    int i;
    public ServerService(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, int i) {
        outputStream=objectOutputStream;
        inputStream=objectInputStream;
        this.i=i;
        new Thread(this).start();
    }


    @Override
    public void run() {
//        inputStream.readIn
        while(true){
            try {
                Thread.sleep(LoopGame.ferekans);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
