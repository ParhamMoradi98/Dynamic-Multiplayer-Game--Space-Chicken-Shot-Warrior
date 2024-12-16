package Frame;

import SecondMenu.SecondMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomFirstMenu extends JPanel {
    JButton vorood;
    JButton deleteUser;
    JButton addUser;
    JPanel secondMenuPanel;

    public BottomFirstMenu() {
        super();
        addObjects();
    }
   private void addObjects(){
       this.setBackground(new Color(0,0,0,0));

       BoxLayout boxLayout=new BoxLayout(this , BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        this.setBorder(new EmptyBorder(new Insets(20,100,20,100)));
    //    this.setPreferredSize(new Dimension(100,100));
       Font font=new Font("علی", 8 ,20);
       vorood = new JButton("ورود");
       vorood.setPreferredSize(new Dimension(200,50));
       vorood.setFont(font);
       this.add(vorood);
       this.add(Box.createGlue());

       deleteUser = new JButton("حذف کاربر");
       deleteUser.setPreferredSize(new Dimension(200,50));

       deleteUser.setFont(font);
       this.add(deleteUser);
       this.add(Box.createGlue());

       addUser= new JButton("افزودن کاربر");
       addUser.setPreferredSize(new Dimension(200,50));

       addUser.setFont(font);
       this.add(addUser);



   }



}
