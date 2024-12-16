package Game.MainPanel;

import Game.Game;
import Game.MousePressed;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import Frame.First_Menu;
import Game.Objects.Chicken.Chicken;
import Game.LoopGame;
import MainClass.Main_Class;
import Reflection.ToURL;
import Save.Save;

public class MainPanel extends JPanel  {
    public static Game game;
    public Method groupMove;
    public boolean isPaused=false;
    public boolean groupMoveFlag=false;
    int c=0;
    public ArrayList<Chicken> chickens= new ArrayList<>();

    private BufferedImage bufferedImage;
    public boolean isPressed=false;
    public LoopGame loopGame;
    private Random random=new Random();
    long chickenTime = System.currentTimeMillis();

    public  MainPanel() {
        background();
        setBounds(0, 0, 1920,1080);
        game = new Game(1920,1080, this);
       loopGame= new LoopGame(this,false);
       loopGame.start();
        mouse();
        for (int i = 0; i < 6; i++) {
            chickens.add(new Chicken(0,i*170,0,0,0,0,0,"null",1,game));
        }
    }

    private void mouse() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                game.getRocket().get(0).setX(e.getX());
                game.getRocket().get(0).setY(e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                game.getRocket().get(0).setX(e.getX());
                game.getRocket().get(0).setY(e.getY());
            }
        });
        addMouseListener(new MouseListener() {
            @Override

            public void mouseClicked(MouseEvent e) {
                if(!isPaused) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        System.out.println(c++);
                        game.getRocket().get(0).shelik("Clicked");

                    }
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        game.getRocket().get(0).throwBomb();

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!isPaused) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        isPressed = true;

                    }


                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isPaused) {
                    if (e.getButton() == MouseEvent.BUTTON1)
                        isPressed = false;

                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        First_Menu.jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keys=e.getExtendedKeyCode();
                if(keys== KeyEvent.VK_ESCAPE){
                    try {
                        Save save=new Save(game);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    loopGame.pauseGame=!loopGame.pauseGame;
                    isPaused=!isPaused;
//                    JFrame jFrame= new JFrame("Stop");
//                    JPanel jPanel = new JPanel();
//                    JButton resume= new JButton("resume");
//                    JButton exit= new JButton("exit");
//                    JButton addMorgh= new JButton("add Morgh");
//                    jFrame.setSize(new Dimension(300,300));
//                    jPanel.add(resume);
//                    jPanel.add(exit);
//                    jPanel.add(addMorgh);
//                    jFrame.add(jPanel);
//                    jFrame.setLocationRelativeTo(null);
             //       jFrame.setVisible(true);
//                    addMorgh.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
                            JFileChooser openFileChooser = new JFileChooser();
                            openFileChooser.showOpenDialog(Main_Class.jframe);
                            File file = openFileChooser.getSelectedFile();

                            String fullName = JOptionPane.showInputDialog("اسم کامل کلاس را وارد کنید:");
                            try {
                                ToURL toURL= new ToURL(file,fullName);
                                groupMove=toURL.getMyMethod();
                                groupMoveFlag=true;
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (MalformedURLException e1) {
                                e1.printStackTrace();
                            } catch (NoSuchMethodException e1) {
                                e1.printStackTrace();
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            } catch (InstantiationException e1) {
                                e1.printStackTrace();
                            }
//                        }
//                    });
//                    resume.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            isPaused=!isPaused;
//                            jFrame.setVisible(false);
//                        }
//                    });
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
       First_Menu.jFrame.requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(!isPaused) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g.drawImage(bufferedImage, 0, 0, null);

            game.paint((Graphics2D) g2);
            if(groupMoveFlag){
                try {
                    groupMove.invoke(null,chickens );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                for (Chicken chicken:chickens){
                    chicken.paint(g2);
                }
            }
           // poshtesareham();

        }

    }

    public void moveGame() {
        if(!isPaused) {
            game.move();
        }
    }

    private void background(){
        try {
            bufferedImage=ImageIO.read(new File("pngs/Game.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void poshtesareham() {
        while (this.isPressed) {

            game.getRocket().get(0).shelik("");
        }


    }
}
