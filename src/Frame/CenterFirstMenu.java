package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CenterFirstMenu extends JPanel {
    JComboBox list_Players;
    File users_file;
    PrintWriter pw;
    BufferedImage bufferedImage;

    public CenterFirstMenu() {
        super();
    //   BoxLayout boxLayout=new BoxLayout(this , BoxLayout.X_AXIS);
     //   this.setLayout(boxLayout);
        this.setBackground(new Color(0,0,0,0));
       // this.add(Box.createGlue());
      //  this.add(Box.createRigidArea(new Dimension(325,300)));

        this.users_file=new File("Game.data");
        Scanner scanner=null;
        try {
            scanner = new Scanner(users_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i=0;
        String last_user= scanner.nextLine();
        String[] players=new String[100];
        while (! last_user.equals("-1")) {
            players[i]=last_user;
            last_user=scanner.next();
            i=i+1;
        }
        String []players_copy=new String[i];
        for (int j = 0; j < i; j++) {
            players_copy[j]=players[j];
        }

        list_Players = new JComboBox(players_copy);
       // list_Players.setPreferredSize(new Dimension(100,100));
       // this.add(Box.createRigidArea(new Dimension(500,500)));
        this.add(list_Players);
        list_Players.setBounds(100,100,100,50);
        Font font=new Font("علي", 8 ,30);
        list_Players.setFont(font);
    //    this.add(Box.createGlue());



    }


}
