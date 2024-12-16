package Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TopFirstMenu extends JPanel {
    JLabel title;

    TopFirstMenu(){
        super();
        addObjects();
    }
    private void addObjects(){
        this.setBackground(new Color(0,0,0,0));

        BoxLayout boxLayout=new BoxLayout(this , BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        this.setBorder(new EmptyBorder(new Insets(20,100,20,100)));
        Font font=new Font("علی", 8 ,40);
        this.add(Box.createGlue());
        title=new JLabel("کاربران : ");
        title.setFont(font);
        title.setForeground(Color.WHITE);
        this.add(title);
        this.add(Box.createGlue());




    }



}
