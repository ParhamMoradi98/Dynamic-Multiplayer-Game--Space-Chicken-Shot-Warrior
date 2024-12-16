package ServerClient;


import Game.MainPanel.MainPanel;
import Game.MousePressed;
import MainClass.Main_Class;
import SecondMenu.SecondMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerWaiting extends JPanel  implements Runnable{
    public static int playersNumber=1;
    int serverPort;
    int maxMarahel;
    int maxTedad;
    ServerSocket serverSocket;
    Socket socket;
    JButton taiid;
    ArrayList<ObjectInputStream>  inputStream = new ArrayList<>();
    ArrayList<ObjectOutputStream> outputStream= new ArrayList<>();
    public static ArrayList<String> playersList= new ArrayList<>();
    Robot robot;
    Font font = new Font("نمونه",Font.BOLD ,20);


    public ServerWaiting(int serverPort,int maxTedad, int maxMarahel) {
        this.serverPort=serverPort;
        this.maxMarahel=maxMarahel;
        this.maxTedad=maxTedad;
        try {
            serverSocket=new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        taiid= new JButton("تایید");
        taiid.setPreferredSize(new Dimension(100,40));
        JLabel jLabel2= new JLabel("لیست بازیکنان");
        jLabel2.setFont(font);
        taiid.setFont(font);
        jLabel2.setPreferredSize(new Dimension(100,40));
        this.add(jLabel2);
        addButtonListener();
        new Thread(this).start();
//    for (int i = 0; i < playersList.size(); i++) {
//            JLabel jLabel=new JLabel(playersList.get(i));
//            this.add(jLabel);
//        }
        SecondMenu.runServer=true;
        this.add(taiid);


    }

    private void addButtonListener() {
        taiid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    robot=new Robot();
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                        cursorImg, new Point(0, 0), "blank cursor");
                Main_Class.jframe.getContentPane().setCursor(blankCursor);
                MainPanel mainPanel=new MainPanel();
                Main_Class.jframe.getContentPane().removeAll();
                Main_Class.jframe.getContentPane().add(mainPanel);
                Main_Class.jframe.getContentPane().revalidate();
                Main_Class.jframe.getContentPane().repaint();
                //  robot.mouseMove(800 , 800);
                MousePressed mousePressed=new MousePressed(mainPanel);
                mousePressed.start();
                GameServer gameServer= new GameServer(outputStream,inputStream);

            }
        });
    }

    public static void showPlayers(){
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println(playersList.get(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < maxTedad; i++) {
                socket = serverSocket.accept();
                outputStream.add(new ObjectOutputStream(socket.getOutputStream()));
                inputStream.add(new ObjectInputStream(socket.getInputStream()));
                ServerService serverService= new ServerService(outputStream.get(i),inputStream.get(i),i);
                try {
                    String name=(String)inputStream.get(i).readObject();
                    Font font1= new Font("نمونه",Font.BOLD ,25);
                    JLabel jLabel= new JLabel(name);
                    jLabel.setFont(font1);
                    this.add(jLabel);
                    ServerWaiting.playersList.add(name);
                    playersNumber++;
                    ServerWaiting.this.revalidate();
                    ServerWaiting.this.repaint();
                    for(String a:ServerWaiting.playersList){
                        System.out.println(a);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                serverService.run();


            }


//            Thread thread =new Thread(ServerWaiting.this);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
