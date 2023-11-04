package project;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
       GameScreen gamescreen;

       GameFrame()
       {
           gamescreen = new GameScreen();
           this.add(gamescreen);
           this.setResizable(false);
           this.setBackground(Color.BLACK);
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.pack();
           this.setVisible(true);
           this.setLocationRelativeTo(null);
       }
}
