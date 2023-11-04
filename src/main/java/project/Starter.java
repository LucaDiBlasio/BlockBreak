package project;

import javax.swing.*;
import java.io.IOException;

public class Starter
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            StartScreen start;
            try {
                start = new StartScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            start.setVisible(true);
        });
    }
}
