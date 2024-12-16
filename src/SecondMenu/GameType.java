package SecondMenu;

import Game.MainPanel.MainPanel;
import Game.MousePressed;
import MainClass.Main_Class;
import ServerClient.ClientWaiting;
import ServerClient.ServerWaiting;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameType extends JPanel {


    JTextField port=new JTextField("2000");

    JTextField marahel=new JTextField("3");
    JTextField tedadBazikon=new JTextField("3");
    JButton taiid= new JButton("تایید");
//    taiid=new JButton("تایید");
    JFrame clientServer;
    JPanel panel;
    private JButton takNafare;
    private JButton chandNafare;
    private JButton back;
    private BufferedImage bufferedImage;
    private Robot robot;
    public GameType() {
        background();
        port.setPreferredSize(new Dimension(150,40));
        marahel.setPreferredSize(new Dimension(100,40));
        tedadBazikon.setPreferredSize(new Dimension(100,40));

        removeAll();
        BoxLayout boxLayoutX = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayoutX);
        setBorder(new EmptyBorder(new Insets(400, 100, 200, 100)));
        setBackground(new Color(0,0,0,0));
        Font font = new Font("نمونه",Font.BOLD ,20);
        takNafare = new JButton("بازی تک نفره");
        takNafare.setFont(font);
        takNafare.setPreferredSize(new Dimension(200 , 40));

        this.add(takNafare);

        this.add(Box.createRigidArea(new Dimension(30, 0)));

        this.add(Box.createGlue());

        chandNafare = new JButton("بازی چند نفره");
        chandNafare.setFont(font);
        chandNafare.setPreferredSize(new Dimension(200 , 40));

        this.add(chandNafare);
        this.add(Box.createRigidArea(new Dimension(30, 0)));
        addActionListener();

    }

    private void addActionListener() {
        taiid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("wtf");
//                int serverPort=(Integer.parseInt(port.getText()));
//                int maxMarahel=(Integer.parseInt(marahel.getText()));
//                int maxTedad=(Integer.parseInt(tedadBazikon.getText()));
//                ServerWaiting serverWaiting= new ServerWaiting(serverPort,maxMarahel,maxTedad);
//                clientServer.getContentPane().removeAll();
//                clientServer.getContentPane().add(serverWaiting);
//                clientServer.getContentPane().revalidate();
//                clientServer.getContentPane().repaint();


                ServerWaiting serverWaiting=new ServerWaiting(2000,2,2);
                clientServer.getContentPane().removeAll();
                clientServer.getContentPane().add(serverWaiting);
                clientServer.getContentPane().revalidate();
                clientServer.getContentPane().repaint();

            }
        });
        takNafare.addActionListener(new ActionListener() {
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
                robot.mouseMove(800 , 800);
                MousePressed mousePressed=new MousePressed(mainPanel);
                mousePressed.start();
            }
        });
        chandNafare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font=new Font("علی", 8 ,20);
                clientServer =new JFrame("Server Client");
                panel= new JPanel();
                clientServer.add(panel);
                panel.add(Box.createRigidArea(new Dimension(30, 0)));
                panel.add(Box.createGlue());
                JButton server= new JButton("    ایجاد سرور    ");
              //  server.setPreferredSize(new Dimension(200 , 40));
                JButton client= new JButton("   اتصال به سرور  ");
            //    client.setPreferredSize(new Dimension(200 , 40));
                clientServer.setSize((new Dimension(500,500)));
                clientServer.setLocationRelativeTo(null);
                BoxLayout boxLayout=new BoxLayout(panel , BoxLayout.Y_AXIS);
                panel.setLayout(boxLayout);
                panel.setBorder(new EmptyBorder(new Insets(20,100,20,100)));
                server.setFont(font);
                client.setFont(font);
                panel.add(server);
                panel.add(Box.createRigidArea(new Dimension(30, 0)));
                panel.add(Box.createGlue());
                panel.add(client);
                panel.add(Box.createRigidArea(new Dimension(30, 0)));
                panel.add(Box.createGlue());
                panel.setBackground(Color.CYAN);
                clientServer.setVisible(true);
                server.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel jPanel=new JPanel();
                        JLabel jLabel= new JLabel("پورت");
                        JLabel jLabel1= new JLabel("حداکثر تعداد بازیکن");
                        JLabel jLabel2= new JLabel("حداکثر تعداد مراحل");

                        jLabel.setFont(font);
                        jLabel1.setFont(font);
                        jLabel2.setFont(font);
                        clientServer.setSize(new Dimension(180,360));
                        jPanel.add(jLabel);

                        jPanel.add(port);
                        jPanel.add(jLabel1);
                        jPanel.add(marahel);
                        jPanel.add(jLabel2);
                        jPanel.add(tedadBazikon);
                        taiid.setFont(font);
                        jPanel.add(taiid);

                        clientServer.getContentPane().removeAll();
                        clientServer.getContentPane().add(jPanel);
                        clientServer.getContentPane().revalidate();
                        clientServer.getContentPane().repaint();

                       // jPanel.setVisible(true);
                    }
                });
                client.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel jPanel=new JPanel();
                        JLabel jLabel= new JLabel("آی پی سرور");
                        JLabel jLabel1= new JLabel("پورت سرور");
                        JTextField ipClient=new JTextField("localhost");
                        JTextField portClient=new JTextField("2000");
                        ipClient.setPreferredSize(new Dimension(100,40));
                        portClient.setPreferredSize(new Dimension(100,40));
                        jLabel.setFont(font);
                        jLabel1.setFont(font);
                        clientServer.setSize(new Dimension(180,300));
                        jPanel.add(jLabel);
                        jPanel.add(ipClient);
                        jPanel.add(jLabel1);
                        jPanel.add(portClient);
                        JButton jButton=new JButton("تایید");
                        jButton.setFont(font);
                        jPanel.add(jButton);
                        clientServer.getContentPane().removeAll();
                        clientServer.getContentPane().add(jPanel);
                        clientServer.getContentPane().revalidate();
                        clientServer.getContentPane().repaint();
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int serverPort = (Integer.parseInt(portClient.getText()));
                                String serverIp = (ipClient.getText());
                                try {
                                    ClientWaiting clientWaiting = new ClientWaiting(serverIp, serverPort);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                            }

                        });
                        // jPanel.setVisible(true);
                    }
                });
                    }
                });

            }


    public void background(){
       // this.setBackground(new Color(0,0,0,0));
        try {
            bufferedImage= ImageIO.read(new File("pngs/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, null);

    }
}
