package SecondMenu;

import SecondMenu.CenterOfSecondMenu.BottomCenterSecondMenu;
import SecondMenu.CenterOfSecondMenu.CenterCenterSecondMenu;
import SecondMenu.CenterOfSecondMenu.TopCenterSecondMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenterSecondMenu extends JPanel {
    CenterCenterSecondMenu centerCenterSecondMenu;
    TopCenterSecondMenu topCenterSecondMenu;
    BottomCenterSecondMenu bottomCenterSecondMenu;
    JButton resume;
    JButton start;
    JButton rankings;
    JLabel hiUser;
    JPanel enterGame;
    CenterSecondMenu(String name){
        generate(name);



    }

    private void generate(String name) {
        this.setBackground(new Color(0,0,0,0));
        BoxLayout boxLayout=new BoxLayout(this , BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(Box.createRigidArea(new Dimension(325,300)));
        addLable(name);
        addButtons();
    }

    private void addButtons() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(Box.createGlue());
        resume=new JButton("       ادامه بازي       ");
        start=new JButton("    شروع بازي جديد    ");
        rankings=new JButton("        رتبه بندي         ");
        Font font=new Font("علي", 8 ,20);
        resume.setPreferredSize(new Dimension(200,50));
        resume.setFont(font);
        this.add(resume);
        this.add(Box.createGlue());
        start.setPreferredSize(new Dimension(200,50));
        start.setFont(font);
        this.add(start);
        this.add(Box.createGlue());
        rankings.setPreferredSize(new Dimension(200,50));
        rankings.setFont(font);
        this.add(rankings);
        this.add(Box.createRigidArea(new Dimension(0,100)));

    }

    private void addLable(String name) {
        Font font=new Font("علي", Font.ITALIC ,30);
        name= "Hi  " +(String)name+   "! ";
        hiUser=new JLabel(name);
        hiUser.setForeground(Color.WHITE);
        hiUser.setFont(font);
        this.add(hiUser);
        //hiuser.setForeground(Color.YELLOW);
//        this.add(Box.createGlue());

    }


}
