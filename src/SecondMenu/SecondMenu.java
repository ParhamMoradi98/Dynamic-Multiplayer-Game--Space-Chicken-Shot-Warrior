package SecondMenu;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.MainPanel.MainPanel;
import Game.MousePressed;
import MainClass.Main_Class;
import Menu.GeneralMenu;
import Frame.First_Menu;


import java.io.File;
import java.io.IOException;

public class SecondMenu extends JPanel {
    GameType gameType;
    public static boolean plane=false;
    JButton back;
    BufferedImage bufferedImage;
    BottomSecondMenu bottomSecondMenu;
    TopSecondMenu topSecondMenu;
    CenterSecondMenu centerSecondMenu;
    public static String user;
    Robot robot;
    public static boolean loaded=false;
    public static boolean runServer=false;

    private GeneralMenu generalMenu;
   public SecondMenu (String User){
       super();

       this.user=User;
       this.setBackground(new Color(0,0,0,0));
       generate();

   }

   private void generate(){

    background();
    this.setLayout(new BorderLayout());
    otherLayouts();
    buttonListener();


   }

private void otherLayouts(){
    bottomSecondMenu=new BottomSecondMenu();
    centerSecondMenu=new CenterSecondMenu(this.user);
    topSecondMenu=new TopSecondMenu();
    this.add(bottomSecondMenu,BorderLayout.SOUTH);
    this.add(centerSecondMenu,BorderLayout.CENTER);
    this.add(topSecondMenu,BorderLayout.NORTH);


}
    private void buttonListener(){
        centerSecondMenu.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType=new GameType();
                SecondMenu.this.removeAll();
                SecondMenu.this.add(gameType);
                SecondMenu.this.revalidate();
                SecondMenu.this.repaint();
            }
        });
        bottomSecondMenu.aboutsUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This is Secret !");
            }
        });
        centerSecondMenu.rankings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "No Rankings Yet ! We will use this in next phase...");

            }
        });
        bottomSecondMenu.setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "No Settings yet !");
            plane=!plane;
            }
        });
        bottomSecondMenu.close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                First_Menu first_menu= null;
                try {
                    first_menu = new First_Menu(First_Menu.jFrame);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                SecondMenu.this.removeAll();
                SecondMenu.this.add(first_menu);
                SecondMenu.this.revalidate();
                SecondMenu.this.repaint();
            }
        });
        centerSecondMenu.resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loaded=true;
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
            }
        });

    }



    private void background(){
     try {
         bufferedImage=ImageIO.read(new File("pngs/background.png"));
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, null);
    }

}
