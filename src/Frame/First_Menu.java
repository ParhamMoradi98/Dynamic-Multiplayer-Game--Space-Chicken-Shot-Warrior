package Frame;

import SecondMenu.SecondMenu;
import javafx.scene.control.ComboBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class First_Menu extends JPanel {
    BottomFirstMenu bottomFirstMenu;
    TopFirstMenu topFirstMenu;
    SecondMenu secondMenu;
    CenterFirstMenu centerFirstMenu;
    BufferedImage bufferedImage;
    public static JFrame jFrame;
    PrintWriter pw;
    public First_Menu(JFrame jFrame) throws IOException {
        super();
        this.jFrame=jFrame;
        generate();
        buttonListener();
    }

    void generate(){
        this.setSize(new Dimension(1000,1000));
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        background();
        otherLayouts();
        this.setVisible(true);
    }

    private void otherLayouts(){

        bottomFirstMenu=new BottomFirstMenu();
        topFirstMenu=new TopFirstMenu();
        centerFirstMenu=new CenterFirstMenu();
        this.add(bottomFirstMenu,BorderLayout.SOUTH);
        this.add(topFirstMenu,BorderLayout.NORTH);
        this.add(centerFirstMenu,BorderLayout.CENTER);

    }





   private void buttonListener(){
        bottomFirstMenu.vorood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondMenu = new SecondMenu(centerFirstMenu.list_Players.getSelectedItem().toString());
                First_Menu.this.removeAll();
                First_Menu.this.add(secondMenu);
                First_Menu.this.revalidate();
                First_Menu.this.repaint();
            }
        });
        bottomFirstMenu.deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            centerFirstMenu.list_Players.removeItemAt(centerFirstMenu.list_Players.getSelectedIndex());
            updateFile();
            }
        });
        bottomFirstMenu.addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String test1= JOptionPane.showInputDialog("Please type the user's name ");
                centerFirstMenu.list_Players.addItem(test1);
                updateFile();

            }
        });
    }
    private void updateFile(){
        try {
            pw=new PrintWriter("Game.data");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        int i=0;
        while (centerFirstMenu.list_Players.getItemAt(i) !=null){
            pw.println(centerFirstMenu.list_Players.getItemAt(i));
            System.out.println(centerFirstMenu.list_Players.getItemAt(i));
            i++;
        }
        pw.println("-1");
        pw.close();
    }

    private void background(){
        this.setBackground(new Color(0,0,0,0));
        try {
            bufferedImage= ImageIO.read(new File("pngs/First.png"));
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
