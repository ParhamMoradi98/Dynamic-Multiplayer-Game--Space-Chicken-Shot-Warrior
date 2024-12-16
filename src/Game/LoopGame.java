package Game;

import Game.MainPanel.MainPanel;

public class LoopGame extends Thread {
    private MainPanel mainPanel;
    public boolean mousePressed;
    public boolean pauseGame=false;
    public  static int ferekans=40;
    public LoopGame (MainPanel mainPanel,boolean mousePressed){
        this.mainPanel=mainPanel;
        this.mousePressed=mousePressed;
    }
    @Override
    public void run() {
        if (true) {
            while (true) {
                if (!pauseGame) {
                    mainPanel.moveGame();
                    mainPanel.repaint();
                  //  mainPanel.poshtesareham();
                    try {
                        Thread.sleep(ferekans);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        else {


        }
    }



}
