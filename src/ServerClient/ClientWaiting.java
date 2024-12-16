package ServerClient;

import Game.Game;
import MainClass.Main_Class;
import Save.Save;
import SecondMenu.SecondMenu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class ClientWaiting implements Runnable{
    String serverIp;
    int serverPort;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    Robot robot;
    public ClientWaiting(String serverIp, int serverPort) throws IOException {
        this.serverIp=serverIp;
        this.serverPort=serverPort;
//        System.out.println("Client IP" + serverIp);
//        System.out.println("Client Port"+ serverPort);
        Socket socket= new Socket(serverIp, serverPort);
        outputStream=new ObjectOutputStream(socket.getOutputStream());
        inputStream= new ObjectInputStream(socket.getInputStream());
        outputStream.writeObject(SecondMenu.user);
     //   ServerWaiting.showPlayers();

        new Thread(this).start();

    }







    @Override
    public void run() {
        ClientPanel clientPanel;
            try {
                Game game= (Game)inputStream.readObject();
                try {
                    robot=new Robot();
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                        cursorImg, new Point(0, 0), "blank cursor");
                Main_Class.jframe.getContentPane().setCursor(blankCursor);
                clientPanel=new ClientPanel(game);
                Main_Class.jframe.getContentPane().removeAll();
                Main_Class.jframe.getContentPane().add(clientPanel);
                Main_Class.jframe.getContentPane().revalidate();
                Main_Class.jframe.getContentPane().repaint();
                while (true){
                    try {
                        outputStream.writeInt(ClientPanel.rocketX);
                        outputStream.reset();

                        outputStream.writeInt(ClientPanel.rocketY);
                        outputStream.reset();

                        outputStream.writeBoolean(ClientPanel.isPressed);
                        outputStream.reset();
                        game= (Game)inputStream.readObject();



                        clientPanel.updateGame(game);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }
                //  robot.mouseMove(800 , 800);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



    }
}
