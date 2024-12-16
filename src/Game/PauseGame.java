package Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PauseGame extends JPanel {
    public JButton resume;
   public JButton close;
    public PauseGame(){
        super();
        addObjects();
    }
    private void addObjects() {
        this.setBackground(Color.RED);
        this.setSize(new Dimension(1080,1920));
        BoxLayout boxLayout=new BoxLayout(this , BoxLayout.X_AXIS);
        this.setLayout(boxLayout);
        this.setBorder(new EmptyBorder(new Insets(20,100,20,100)));
        resume=new JButton("ادامه بازی");
        close=new JButton("خروج");
        Font font=new Font("علی", 8 ,20);
        close.setPreferredSize(new Dimension(200,50));
        close.setFont(font);
        this.add(close);
        this.add(Box.createGlue());
        resume.setPreferredSize(new Dimension(200,50));
        resume.setFont(font);
        this.add(resume);
        this.add(Box.createGlue());
        buttonListner();

    }

    private void buttonListner() {

    }

}
