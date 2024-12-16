package MainClass;

import Frame.First_Menu;
import Frame.Main_Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main_Class {
    public static Main_Frame jframe;
    public static void main(String[] args) throws IOException {
        jframe=new Main_Frame();
        First_Menu jpanel=new First_Menu(jframe);
        jframe.add(jpanel);
        jframe.setVisible(true);

    }

}
