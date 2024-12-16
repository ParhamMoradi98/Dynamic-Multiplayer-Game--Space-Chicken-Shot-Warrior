package Frame;

import javax.swing.*;
import java.awt.*;

public class Main_Frame extends JFrame{
    public static Container my_Container;


    public Main_Frame(){
        super("Chicken1 Invaders");
        this.setSize(new Dimension(1920,1080));
      //  my_Container=this.getContentPane();
        //JButton jButton=new JButton("My container");
       // jButton.setPreferredSize(new Dimension(50,100));
       // my_Container.add(jButton);
        //this.add(my_Container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        this.setVisible(true);

    }


}
