package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class StartScreen extends JFrame
{

    public StartScreen() throws IOException {

        setTitle("BlockBreak: Java Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setSize(800,550);
        setLocationRelativeTo(null);

        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
        Color myColor=new Color(10,10,13);
        pan.setBackground(myColor);

        JLabel game = new JLabel("BlockBreak: Java Edition");
        game.setForeground(Color.CYAN);
        game.setFont(new Font("Times New Roman",Font.BOLD,60));
        game.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton play = new JButton("Play New Game");
        play.setBackground(pan.getBackground());
        play.setForeground(Color.GREEN);
        play.setFocusPainted(false);
        play.setFont(new Font("Times New Roman", Font.BOLD,36));
        play.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exit = new JButton("Exit Game");
        exit.setBackground(pan.getBackground());
        exit.setForeground(Color.RED);
        exit.setFocusPainted(false);
        exit.setFont(new Font("Times New Roman", Font.BOLD,36));
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        pan.add(Box.createRigidArea(new Dimension(0,80)));
        pan.add(game);
        pan.add(Box.createRigidArea(new Dimension(0,120)));
        pan.add(play);
        pan.add(Box.createRigidArea(new Dimension(0,50)));
        pan.add(exit);


        exit.addActionListener(act->
        {
            dispose();
        });

        play.addActionListener(act->
        {
            dispose();
            GameFrame frame=new GameFrame();
            frame.setVisible(true);
        });

        getContentPane().add(pan);
    }
}
