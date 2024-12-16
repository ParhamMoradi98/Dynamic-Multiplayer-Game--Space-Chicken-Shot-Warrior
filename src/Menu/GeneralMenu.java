package Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GeneralMenu extends JPanel {
    GeneralMenu(){
        super();
        generate();
       // buttonListener();
    }
    void generate(){
        this.setSize(new Dimension(500,500));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(new Insets(25,25,25,25)));
        this.setPreferredSize(new Dimension(500,500));
   //     otherLayouts();
        this.setVisible(true);
    }


}
