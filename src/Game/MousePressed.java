package Game;

import Game.MainPanel.MainPanel;

public class MousePressed extends Thread {

    private MainPanel mainPanel;
    public MousePressed(MainPanel mainPanel){
        System.out.println("Thread started");
        this.mainPanel=mainPanel;
    }
    @Override
    public void run (){
        while (true) {
                mainPanel.poshtesareham();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


}
